package basiccrud;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;

public class FrameCreate extends JFrame 
{
    private JButton btnCreate;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JTextField txtProduto;
    private JTextField txtQuantidade;
    private JTextField txtValor;
    
    public FrameCreate() {
        componentes();
        this.setLocationRelativeTo(null); // inicializa o frame no meio da tela
    }
                        
    private void componentes() {

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        txtProduto = new JTextField(10);
        txtQuantidade = new JTextField(10);
        txtValor = new JTextField(10);
        btnCreate = new JButton();

        jLabel1.setText("Produto");

        jLabel2.setText("Quantidade");

        jLabel3.setText("Valor");

        btnCreate.setText("Cadastrar produto");
        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });
        
        // tela
        setTitle("Cadastro de produto"); // título da janela
        setSize(215,180);
        setResizable(false);
        
        // container com layout
        Container painel = getContentPane(); // painel
        painel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 8));
        
        // labels e campos texto
        painel.add(jLabel1);
        painel.add(txtProduto);
        painel.add(jLabel2);
        painel.add(txtQuantidade);
        painel.add(jLabel3);
        painel.add(txtValor);
        
        // botões
        painel.add(btnCreate);
        btnCreate.setToolTipText("Clique aqui para cadastrar um produto");                             
    }                       

    private void btnCreateActionPerformed(ActionEvent evt) {                                          
        try
        {
            String produto = txtProduto.getText();
            int qtd = (txtQuantidade.getText().isEmpty() ? 0 : Integer.parseInt(txtQuantidade.getText())); 
            double vlr = (txtValor.getText().isEmpty() ? 0.0 : Double.parseDouble(txtValor.getText())); 
            
            if( !produto.isEmpty() && qtd > 0 && vlr > 0.0 )
            {
                LocalDate dt = LocalDate.now();                
                Produto p = new Produto();
                
                p.setId(++Produto.count);
                p.setNome(produto);
                p.setQtd(qtd);
                p.setVlr(vlr);
                p.setDtAdicionado(dt);
                
                Produto.hp.add(p); // Adiciona a variável estática o novo valor
                
                //Produto.GravarArquivo();
                
                JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
            }
            else { JOptionPane.showMessageDialog(null, "Algo está errado"); }
        }
        catch(NumberFormatException e){ JOptionPane.showMessageDialog(null, e.getMessage()); } // Exibe o erro
    }                                         

  
    public static void main(String args[]) {
        
        EventQueue.invokeLater(new Runnable() { // eventqueue é uma requisição para abrir uma janela do thread do swing. já que o comando de abrir a janela vem de outra classe, esse é o procedimento padrão
            public void run() {
                new FrameCreate().setVisible(true);
            }
        });
    }                                             
}
