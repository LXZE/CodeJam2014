import java.util.*;
import java.io.*;

public class codejam4{
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("D-large.in"));
		BufferedWriter write = new BufferedWriter(new FileWriter("output.txt"));

		int caseN = sc.nextInt();
		sc.nextLine();
		String[] allCase = new String[caseN];

		int win = 0;
		int dwin = 0;

		ArrayList<Float> naomi,naomi_temp,ken,ken_temp;
		int time;
		boolean pop = false;

		for(int icase=0;icase<caseN;icase++){
			time = sc.nextInt();
			sc.nextLine();
			naomi = new ArrayList<Float>();
			ken = new ArrayList<Float>();

			for(int round=0;round<time;round++)
				naomi.add(sc.nextFloat());
			for(int round=0;round<time;round++)
				ken.add(sc.nextFloat());

			naomi_temp = new ArrayList<Float>(naomi);
			ken_temp = new ArrayList<Float>(ken);

			Collections.reverse(naomi);
			Collections.sort(ken,Collections.reverseOrder());

			for(int chk=time-1;chk>=0;chk--){
				for(int j=chk;j>=0 && !pop;j--){
					if(ken.get(j)>naomi.get(chk)){
						naomi.remove(chk);
						ken.remove(j);
						pop = true;
					}
					else if(ken.get(0) < naomi.get(chk)){
						naomi.remove(chk);
						ken.remove(chk);
						pop = true;
						win++;
					}
				}
				pop = false;
			}

			pop=false;

			naomi = new ArrayList<Float>(naomi_temp);
			ken = new ArrayList<Float>(ken_temp);


			Collections.sort(naomi,Collections.reverseOrder());
			Collections.sort(ken);

			for(int chk=time-1;chk>=0;chk--){
				for(int k=chk;k>=0 && !pop;k--){
					if(naomi.get(chk)>ken.get(k)){
						naomi.remove(chk);
						ken.remove(k);
						pop=true;
						dwin++;
					}
					else if(k==0 && naomi.get(chk)<ken.get(k)){
						naomi.remove(chk);
						ken.remove(chk);
						pop=true;
					}

				}
				pop = false;
				//System.out.println(naomi+"\n"+ken);
			}
			allCase[icase] = "Case #"+(icase+1)+": "+dwin+" "+win;
			//System.out.println(dwin +", "+win);

			win=0;
			dwin=0;
		}


		for(int i=0;i<allCase.length;i++){
			//System.out.println(allCase[i]);
			write.append(allCase[i]+"\n");
		}
		write.close();

	}

}