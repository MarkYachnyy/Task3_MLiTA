package ru.cs.vsu.yachnyy_m_a;

import ru.cs.vsu.yachnyy_m_a.util.JTableUtils;
import ru.cs.vsu.yachnyy_m_a.util.SwingUtils;

import javax.swing.*;
import java.util.function.Consumer;

public class ManualMatrixInputDialogue extends JFrame{
    private JPanel PanelMain;
    private JTable TableInput;
    private JButton ButtonConfirm;

    public ManualMatrixInputDialogue(Consumer<int[][]> consumer){
        this.setTitle("Ручной ввод матрицы");
        this.setContentPane(PanelMain);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(TableInput, 50, false, false, true, true);
        TableInput.setRowHeight(50);

        ButtonConfirm.addActionListener(event -> {
            try{
                int[][] matrix = JTableUtils.readIntMatrixFromJTable(TableInput);
                if(matrix[0].length - matrix.length != 1){
                    SwingUtils.showInfoMessageBox("Матрица должна быть размером N x (N+1)!");
                } else {
                    consumer.accept(matrix);
                    this.setVisible(false);
                }
            } catch (Exception e){
                SwingUtils.showErrorMessageBox(e);
            }

        });
    }

}
