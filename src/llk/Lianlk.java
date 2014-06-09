package llk;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import win32.Mouse;
import win32.Window;

public class Lianlk
{

	// 秒杀
	public static void ms()
	{
		// 取游戏窗口句柄
		int hwnd = Window.getHwnd("QQ游戏 - 连连看角色版");
		if (hwnd <= 0)
		{
			return;
		}
		try
		{
			// 游戏截图( 游戏窗口不能被其它窗口遮挡 .否则截图会截到其它窗口 )
			File f = File.createTempFile("qqllk", ".bmp");
			System.out.println(f.getAbsolutePath());
			// 写出位图
			BufferedImage buffImage = Window.getImage(hwnd);
			if (buffImage == null)
			{
				return;
			}
			ImageIO.write(buffImage, "bmp", new FileOutputStream(f));
			// 解析位图
			BMP bmp = new BMP(f.getAbsolutePath());

			// QQ连连看是 11 * 19 矩阵
			int[][] n = new int[11][19];
			FkSet set = new FkSet();
			for (int i = 0; i < 11; i++)
			{
				for (int j = 0; j < 19; j++)
				{
					// 截取一小块 15 *15 的数据
					int x = 17 + j * 31;
					int y = 187 + i * 35;
					Fangkuai fk = new Fangkuai(bmp.getData(x, y, 15, 15));
					if (bmp.getColor(x, y) != 7359536)
					{// 这个值是空白区的颜色值
						int type = set.add(fk);
						if (type != 0)
						{
							n[i][j] = type;
						}
					}

				}
			}
			System.out.println(set.size());
			// 鼠标自动点击
			DepthFirst df = new DepthFirst(n);
			while (df.search())
			{
				Point a = df.getA();
				Point b = df.getB();
				Mouse.click(hwnd, a.getY() * 31 + 17, a.getX() * 35 + 187);
				Mouse.click(hwnd, b.getY() * 31 + 17, b.getX() * 35 + 187);
				df.setValue(a.getX(), a.getY(), 0);
				df.setValue(b.getX(), b.getY(), 0);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		Lianlk.ms();
	}
}
