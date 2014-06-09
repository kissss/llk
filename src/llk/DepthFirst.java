package llk;

import java.util.Stack;

public class DepthFirst {
	/*
	 * public static void main(String[] args) { int[][] a = { { 6, 1, 2, 1, 0, 6
	 * }, { 0, 0, 5, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 2, 5, 0, 0, 0 } };
	 * DepthFirst df = new DepthFirst(a); if (df.search()) {
	 * System.out.println(df.getA().getX() + "," + df.getA().getY());
	 * System.out.println(df.getB().getX() + "," + df.getB().getY()); } else {
	 * System.out.println("û�ҵ�"); } }
	 */
	private Stack<Point> s;
	private int[][] v;
	private Point a;
	private Point b;
	private int x, y, dx, dy, i, j;

	public DepthFirst(int[][] v) {
		this.v = v;
	}

	public Point getA() {
		return a;
	}

	public Point getB() {
		return b;
	}

	public void setValue(int x, int y, int value) {
		v[x][y] = value;
	}

	// ��һ�Խ�.������V�����е�����
	public boolean search() {
		s = new Stack<Point>();
		x = v.length;
		y = v[0].length;

		for (i = 0; i < x; i++) {
			for (j = 0; j < y; j++) {
				if (v[i][j] != 0) {// 0 �ǿհ���
					dx = i;
					dy = j;

					if (up(3) || down(3) || left(3) || right(3))
						return true;

				}
			}
		}

		return false;
	}

	// ��
	private boolean up(int c) {
		if (--c < 0)
			return false;
		int k;
		push();
		while (true) {
			k = $(3);
			if (k == 0)
				break;
			if (k == 1)
				return true;
			if (k == 2)
				break;
			if (left(c))
				return true;
		}
		peek();
		while (true) {
			k = $(4);
			if (k == 0)
				break;
			if (k == 1)
				return true;
			if (k == 2)
				break;
			if (right(c))
				return true;
		}
		pop();
		return false;
	}

	// ��
	private boolean down(int c) {
		if (--c < 0)
			return false;
		int k;
		push();
		while (true) {
			k = $(3);
			if (k == 0)
				break;
			if (k == 1)
				return true;
			if (k == 2)
				break;
			if (left(c))
				return true;
		}
		peek();
		while (true) {
			k = $(4);
			if (k == 0)
				break;
			if (k == 1)
				return true;
			if (k == 2)
				break;
			if (right(c))
				return true;
		}
		pop();
		return false;
	}

	// ��
	private boolean left(int c) {
		if (--c < 0)
			return false;
		int k;
		push();
		while (true) {
			k = $(1);
			if (k == 0)
				break;
			if (k == 1)
				return true;
			if (k == 2)
				break;
			if (up(c))
				return true;
		}
		peek();
		while (true) {
			k = $(2);
			if (k == 0)
				break;
			if (k == 1)
				return true;
			if (k == 2)
				break;
			if (down(c))
				return true;
		}
		pop();
		return false;
	}

	// ��
	private boolean right(int c) {
		if (--c < 0)
			return false;
		int k;
		push();
		while (true) {
			k = $(1);
			if (k == 0)
				break;
			if (k == 1)
				return true;
			if (k == 2)
				break;
			if (up(c))
				return true;
		}
		peek();
		while (true) {
			k = $(2);
			if (k == 0)
				break;
			if (k == 1)
				return true;
			if (k == 2)
				break;
			if (down(c))
				return true;
		}
		pop();
		return false;
	}

	// ��һ�� XXX( 1,2,3,4 �������� )
	private int $(int xxx) {

		switch (xxx) {
		case 1:
			dx--;
			break;
		case 2:
			dx++;
			break;
		case 3:
			dy--;
			break;
		case 4:
			dy++;
		}

		if (dx < 0 || dx >= x || dy < 0 || dy >= y) { // Խ��
			return 0;
		}

		if (v[i][j] == v[dx][dy]) { // �ҵ�һ��
			a = new Point(i, j);
			b = new Point(dx, dy);
			return 1;
		}
		// ����3 = ������ .����2 = ���ϰ�
		return v[dx][dy] != 0 ? 2 : 3;
	}

	// =================

	private void pop() {
		Point p = s.pop();
		dx = p.getX();
		dy = p.getY();
	}

	private void peek() {
		Point p = s.peek();
		dx = p.getX();
		dy = p.getY();
	}

	private void push() {
		s.push(new Point(dx, dy));
	}

}
