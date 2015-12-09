/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jurls.examples.approximation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thorsten
 */
public class FunctionRenderer1D extends javax.swing.JPanel {

    public final List<RenderFunction1D> renderFunctions = new ArrayList<>();

    /**
     * Creates new form FunctionRenderer
     */
    public FunctionRenderer1D() {
        initComponents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (RenderFunction1D rf : renderFunctions) {
            g.setColor(rf.getColor());

            double prevX = 0;
            double prevY = 0;
            for (double x = 0; x < 1.0; x += 0.01) {
                double y0 =rf.compute(x);
                double y = (0.5f * y0+0.5)*getHeight();
                int px = (int)(getWidth() * x);
                g.drawLine((int) prevX, (int) prevY, (int) px, (int) y);
                prevX = px;
                prevY = y;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
