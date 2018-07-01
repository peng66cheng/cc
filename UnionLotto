import java.util.*;
// 双色球
// 从01-33共33个红色号码球中选择6个号码，并从01-16共16个蓝色号码球中选择1个号码，组合为一注投注号码的基本投注
public class UnionLotto {

	//生成红球	
	public Set<Integer> generateRed(){
		Set<Integer> redSet = new TreeSet<Integer>();
		Random ran=new Random();
		while(redSet.size() < 6){
			redSet.add(ran.nextInt(33)+1);
		}

		return redSet;
	}

	//生成蓝球
	public Integer generateBlue(){
		return new Random().nextInt(16)+1;
	}

	public static void main(String[] args) {
		UnionLotto unionLotto = new UnionLotto();
		Set<Integer> redSet = unionLotto.generateRed();
		Integer blue = unionLotto.generateBlue();
		System.out.println("本期产生的红色球为： "+redSet);
		System.out.println("本期产生的蓝色球为： "+blue);
	}

}
