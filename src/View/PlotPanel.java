package View;

import Controller.EvaluateValues;
import org.math.plot.Plot2DPanel;
import org.math.plot.plots.LinePlot;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlotPanel extends Plot2DPanel {

    private List<double[]> bestData;
    private List<double[]> averageData;
    private List<double[]> worstData;

    public PlotPanel() {
        initGUI();
    }

    private void initGUI() {
        addLegend("SOUTH");
        setAxisLabel(0, "Generations");
        setAxisLabel(1, "Fitness");

        LinePlot bestLine = new LinePlot("Best", Color.green, new double[][]{{0, 0}});
        LinePlot averageLine = new LinePlot("Average", Color.pink, new double[][]{{0, 0}});
        LinePlot worstLine = new LinePlot("Worst", Color.BLUE, new double[][]{{0, 0}});

        addPlot(bestLine);
        addPlot(averageLine);
        addPlot(worstLine);

        init(0);
    }

    public void init(int numGenerations) {
        bestData = new ArrayList<>(numGenerations+1);
        averageData = new ArrayList<>(numGenerations+1);
        worstData = new ArrayList<>(numGenerations+1);
    }
    
    public void update(int generation, EvaluateValues evaluationResult) {
        //recibo los datos por paramets

        bestData.add(new double[]{generation, evaluationResult.bestFitness});
        averageData.add(new double[]{generation, evaluationResult.averageFitness});
        worstData.add(new double[]{generation, evaluationResult.worstFitness});

        reloadData(generation);
    }

    private void reloadData(int generation) {
        changePlotData(0, bestData.toArray(new double[generation+1][]));
        changePlotData(1, averageData.toArray(new double[generation+1][]));
        changePlotData(2, worstData.toArray(new double[generation+1][]));

        setAutoBounds(1);
        setFixedBounds(0, 0, generation);
    }

}
