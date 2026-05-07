/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.funcionalidadcomprarmembresiausuarionoregistrado.presentacion;



/**
 *
 * @author Diego
 */
//aqui el plan es que si no se lleno el textfiel con la membresia
//el boton no se pueda utilizar y si le da click ahi ponga
//esta funcion solo esta en el plan oro, actualizalo
//o algo asi
public class BienvenidaFORM extends javax.swing.JFrame {

    private ControlForms control;

    public BienvenidaFORM(ControlForms control) {
        this.control = control;
        this.setTitle("Bienvenida");
        initComponents();
        configiracionPorMembresia();
        
//        Cliente cliente = control.getClienteActual();
//        if (cliente != null && cliente.getMembresíaComprada() != null) {
//            String plan = cliente.getMembresíaComprada().getMembresia().getTipoMembresia().toString();
//            lblTitulo.setText("Hola " + cliente.getNombre() + ", tu plan actual es: " + plan);
//        }
    }

    private void configiracionPorMembresia() {
//        Membresia membresia = control.getMembresiaSeleccionada();
//        if (membresia == null) {
//            return;
//        }

//        TipoMembresia tipoMembresia = membresia.getTipoMembresia();
//
//        btnCursos.setEnabled(false);
//        btnNutricion.setEnabled(false);
//        btnAmbienteMusical.setEnabled(false);
//        btnProgreso.setEnabled(false);
//
//        switch (tipoMembresia) {
//            case ORO:
//                btnCursos.setEnabled(true);
//                btnProgreso.setEnabled(true);
//            case PLATA:
//                btnNutricion.setEnabled(true);
//                btnAmbienteMusical.setEnabled(true);
//            case BRONCE:
//                break;
//        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        textAreaInstalaciones = new javax.swing.JTextArea();
        btnCursos = new javax.swing.JButton();
        btnNutricion = new javax.swing.JButton();
        btnAmbienteMusical = new javax.swing.JButton();
        btnProgreso = new javax.swing.JButton();
        btnQuejasSugerencias = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel.setBackground(new java.awt.Color(18, 18, 18));

        jSeparator1.setBackground(new java.awt.Color(225, 6, 0));
        jSeparator1.setForeground(new java.awt.Color(225, 6, 0));

        lblTitulo.setBackground(new java.awt.Color(255, 255, 255));
        lblTitulo.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Bienvenido: \"Nombre\"");

        jScrollPane5.setBorder(null);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textAreaInstalaciones.setEditable(false);
        textAreaInstalaciones.setBackground(new java.awt.Color(18, 18, 18));
        textAreaInstalaciones.setColumns(20);
        textAreaInstalaciones.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        textAreaInstalaciones.setForeground(new java.awt.Color(255, 255, 255));
        textAreaInstalaciones.setRows(5);
        textAreaInstalaciones.setText("¡Bienvenido a Fit Life GYM!\nAhora formas parte de un gimnasio diseñado para ayudarte a entrenar,\nProgresar y alcanzar tus objetivos");
        jScrollPane5.setViewportView(textAreaInstalaciones);

        btnCursos.setBackground(new java.awt.Color(44, 44, 44));
        btnCursos.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnCursos.setForeground(new java.awt.Color(255, 255, 255));
        btnCursos.setText("Cursos Especiales");
        btnCursos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCursos.addActionListener(this::btnCursosActionPerformed);

        btnNutricion.setBackground(new java.awt.Color(44, 44, 44));
        btnNutricion.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnNutricion.setForeground(new java.awt.Color(255, 255, 255));
        btnNutricion.setText("Centro de nutrición");
        btnNutricion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnNutricion.addActionListener(this::btnNutricionActionPerformed);

        btnAmbienteMusical.setBackground(new java.awt.Color(44, 44, 44));
        btnAmbienteMusical.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnAmbienteMusical.setForeground(new java.awt.Color(255, 255, 255));
        btnAmbienteMusical.setText("Ambiente Musical");
        btnAmbienteMusical.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAmbienteMusical.addActionListener(this::btnAmbienteMusicalActionPerformed);

        btnProgreso.setBackground(new java.awt.Color(44, 44, 44));
        btnProgreso.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnProgreso.setForeground(new java.awt.Color(255, 255, 255));
        btnProgreso.setText("Progreso Fisico");
        btnProgreso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnProgreso.addActionListener(this::btnProgresoActionPerformed);

        btnQuejasSugerencias.setBackground(new java.awt.Color(51, 51, 51));
        btnQuejasSugerencias.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnQuejasSugerencias.setForeground(new java.awt.Color(255, 255, 255));
        btnQuejasSugerencias.setText("Quejas o Sugerencias");
        btnQuejasSugerencias.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnQuejasSugerencias.addActionListener(this::btnQuejasSugerenciasActionPerformed);

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(btnProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(btnCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btnNutricion, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(btnAmbienteMusical, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 49, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnQuejasSugerencias, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNutricion, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAmbienteMusical, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnQuejasSugerencias, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 860, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 466, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCursosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCursosActionPerformed

    private void btnNutricionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNutricionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNutricionActionPerformed

    private void btnAmbienteMusicalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmbienteMusicalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAmbienteMusicalActionPerformed

    private void btnProgresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProgresoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProgresoActionPerformed

    private void btnQuejasSugerenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuejasSugerenciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuejasSugerenciasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAmbienteMusical;
    private javax.swing.JButton btnCursos;
    private javax.swing.JButton btnNutricion;
    private javax.swing.JButton btnProgreso;
    private javax.swing.JButton btnQuejasSugerencias;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextArea textAreaInstalaciones;
    // End of variables declaration//GEN-END:variables
}
