package llk;

import java.util.ArrayList;
import java.util.List;

// �������治ͬ����ķ��顣�����ͬһ�֣��Ͳ��ظ�������
public class FkSet
{

	private List<Fangkuai> list = new ArrayList<Fangkuai>();

	public int size()
	{
		return list.size();
	}

	// ����������
	public int add(Fangkuai fk)
	{
		for (int i = 0; i < list.size(); i++)
		{
			Fangkuai f = list.get(i);
			if (fk.same(f))
			{
				return i + 1;
			}
		}
		list.add(fk);
		return list.size();
	}

}
