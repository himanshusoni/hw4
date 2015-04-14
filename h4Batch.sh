a=0

declare -a dataSet=("breast-cancer.arff"
	"EEGEyeState.arff"
	"seismic-bumps.arff"
	"supermarket.arff"
)
declare -a method=("weka.classifiers.meta.Bagging"
	"weka.classifiers.meta.AdaBoostM1"
	"vanilla"
)
declare -a classifierAlgo=("weka.classifiers.trees.J48 -- -C 0.25 -M 2"
	"weka.classifiers.trees.DecisionStump"
	"weka.classifiers.functions.Logistic -- -R 1.0E-8 -M -1"
)
declare -a iter=("30"
	"100"
	"150"
)


wekaPath="../weka-3-6-12/weka.jar"

for i in "${iter[@]}"
do
	for data in "${dataSet[@]}"
	do
		for k in "${classifierAlgo[@]}"
		do
			for j in "${method[@]}"
			do
				echo "Iterations : $i , File : $data , Method : $j , classifier : $k"
				if [ "$j" = "vanilla" ]; then
					temp="${k/-- /}"
					java -Xmx2g -cp $wekaPath $temp -o \
					-t $data \
					> $a
					# echo "Vanilla"
				else
					java -Xmx2g -cp $wekaPath $j -P 100 -o -S 1 -I $i \
					-t $data \
					-W $k \
					> $a
					# echo "mix"
				fi
				a=`expr $a + 1`
				echo "------------------done--------------------"
			done
		done
	done
done

java GenerateReport > outputReport.txt