package basiccrud;

import java.text.*;
import java.io.*;
import java.util.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;

public class FrameRead extends JFrame 
{
    Iterator<Produto> it = Produto.hp.iterator();
    private int count = 0; // Variável que contará quantos produtos já foram visualizados (Para usar em conjunto com o iterator)
    
    private JButton btnProximo;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JTextField txtData;
    private JTextField txtIndex;
    private JTextField txtProduto;
    private JTextField txtQuantidade;
    private JTextField txtValor;
    
    public FrameRead() 
    {
        componentes();
        txtProduto.setEnabled(false); // Desativa botão
        txtQuantidade.setEnabled(false); // Desativa botão
        txtValor.setEnabled(false); // Desativa botão
        txtIndex.setEnabled(false); // Desativa botão
        txtData.setEnabled(false); // Desativa botão
        
        this.setLocationRelativeTo(null); // Centralizar
        
        if(it.hasNext()) // Se tiver mais produtos
        {
            Produto p = it.next(); // Armazena o proximo produto
            
            txtProduto.setText(p.getNome()); // Muda o texto de acordo com a classe Produto
            txtQuantidade.setText(String.valueOf(p.getQtd())); // Muda o texto de acordo com a classe Produto (Converte para string)
            txtValor.setText(String.valueOf(p.getVlr())); // Muda o texto de acordo com a classe Produto (Converte para string)
            txtIndex.setText(String.valueOf(p.getId())); // Muda o texto de acordo com a classe Produto (Converte para string)
            
            try
            {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(p.getDtAdicionado())); // Formatação de DATE
                String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date); // Formatação de SimpleDateFormat
                txtData.setText(formattedDate); // Muda o texto do campo txtData
            }
            catch(Exception e){ txtData.setText(String.valueOf(p.getDtAdicionado())); }
            // Se der algum erro na hora de converter, ele põe sem formato mesmo
            
            count = 1;
            
            if(!it.hasNext()) // Se não tiver mais cadastros
                btnProximo.setEnabled(false); // Então desativa o botão de proximo
        }
    }
                          
    private void componentes() {

        btnProximo = new JButton();
        txtQuantidade = new JTextField(18);
        txtValor = new JTextField(18);
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        txtProduto = new JTextField(18);
        jLabel4 = new JLabel();
        txtIndex = new JTextField(18);
        txtData = new JTextField(18);
        jLabel5 = new JLabel();

        btnProximo.setText("Próximo");
        btnProximo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnProximoActionPerformed(evt);
            }
        });

        jLabel1.setText("Produto");

        jLabel2.setText("Quantidade");

        jLabel3.setText("Valor");

        jLabel4.setText("Index");

        jLabel5.setText("Criado em");

        // tela
        setTitle("Busca de produto"); // título da janela
        setSize(230,350);
        setResizable(false);
        
        // container com layout
        Container painel = getContentPane(); // painel
        painel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 8));
        
        // labels e campos texto
        painel.add(jLabel4);
        painel.add(txtIndex);
        painel.add(jLabel1);
        painel.add(txtProduto);
        painel.add(jLabel2);
        painel.add(txtQuantidade);
        painel.add(jLabel3);
        painel.add(txtValor);
        painel.add(jLabel5);
        painel.add(txtData);              
        
        // botões
        painel.add(btnProximo);
        btnProximo.setToolTipText("Clique aqui para mostrar o próximo produto");               
    }                      

    private void btnProximoActionPerformed(ActionEvent evt) {                                           
        Produto p = it.next();
        
        txtProduto.setText(p.getNome()); // Muda o texto de acordo com a classe Produto
        txtQuantidade.setText(String.valueOf(p.getQtd())); // Muda o texto de acordo com a classe Produto (Converte para string)
        txtValor.setText(String.valueOf(p.getVlr())); // Muda o texto de acordo com a classe Produto (Converte para string)
        txtIndex.setText(String.valueOf(p.getId())); // Muda o texto de acordo com a classe Produto (Converte para string)
       
        try
        {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(p.getDtAdicionado())); // Formatação de DATE
            String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date); // Formatação de SimpleDateFormat
            txtData.setText(formattedDate); // Muda o texto do campo txtData
        }
        catch(Exception e){ txtData.setText(String.valueOf(p.getDtAdicionado())); }
        // Se der algum erro na hora de converter, ele poe a sem formato mesmo
        
        count++;
        
        if( count >= Produto.hp.size() ) // Se não tiver mais cadastros
            btnProximo.setEnabled(false); // Então desativa o botão de proximo
    }                                          

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() { // eventqueue é uma requisição para abrir uma janela do thread do swing. já que o comando de abrir a janela vem de outra classe, esse é o procedimento padrão
            public void run() {
                new FrameRead().setVisible(true);
            }
        });
    }                                               
}
