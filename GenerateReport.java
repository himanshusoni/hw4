import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GenerateReport{
	String[] dataSet={
			"breast-cancer.arff",
			"EEGEyeState.arff",
			"seismic-bumps.arff",
			"supermarket.arff"
			};
	String[] method={
			"Bagging",
			"AdaBoost1",
			"vanilla"
			};
	String[] classifierAlgo={
			"J48",
			"DecisionStump",
			"Logistic Regression"
			};
	int[] iter={30,50,100};

	public static void main(String[] args){

		new GenerateReport().run();
	}
	public void run(){
		int totalFiles = dataSet.length * method.length * classifierAlgo.length * iter.length;

		BufferedReader br = null;
		try{
			for(int i = 0; i < totalFiles;i++){
				if(i%36==0)
					System.out.println("\n\n" + "Iterations : " + iter[i/36]);
				if(i%9 == 0)
				{
					System.out.println("\n\nDataSet : "+dataSet[(i/9)%dataSet.length]);
					System.out.print("Base Learner,Bagging,Boosting,Vanilla");
				}
				
				if(i%3 == 0){
					System.out.print("\n"+classifierAlgo[(i%9)/3]+",");
				}
				
				String sCurrentLine = "";
				br = new BufferedReader(new FileReader(i+""));
				while ((sCurrentLine = br.readLine()) != null) {					
					if(sCurrentLine.equals("=== Error on test split ===")){
						sCurrentLine = br.readLine();
						sCurrentLine = br.readLine();
						sCurrentLine = br.readLine();
						String[] splitLine = sCurrentLine.split("\\s+");

						float error = Float.parseFloat(splitLine[4]);
						System.out.print(error+",");
					}
				}
				br.close();
			}
		}catch(IOException e){

		}
	}
}