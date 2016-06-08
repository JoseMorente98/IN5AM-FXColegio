/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.utilidades;

import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class ReportGenerator {
    private static ReportGenerator instance;

    private ReportGenerator() {
    }

    public static ReportGenerator getInstance() {
        if (instance == null) {
            instance = new ReportGenerator();
        }
        return instance;
    }
    
    public void generate(Map parameters, String reportFile, String title) {
        try {
            InputStream reporte = getClass().getResourceAsStream("org/josemorente/recursos/" + reportFile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reporte);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, SQLDatabaseConnection.getInstance().getConnection());
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setTitle(title);
            jasperViewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ReportGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
