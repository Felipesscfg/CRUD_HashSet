package basiccrud;

import java.text.*;
import java.io.*;
import java.util.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;

public class FrameUpdate extends JFrame 
{   
    private Produto p; // Variável que armazena o produto recebido no construtor
    
    private JButton btnUpdate;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JTextField txtId;
    private JTextField txtProduto;
    private JTextField txtQuantidade;
    private JTextField txtValor;
    
    public FrameUpdate(){ componentes(); } // Construtor padrão, necessário estar aqui mesmo que não se use
    
    public FrameUpdate(Produto p){ // Construtor que recebe um Produto como parâmetro (Só pode ser aberto assim para dar UPDATE)
        this(); 
                
        txtProduto.setText(p.getNome()); // Muda o texto de acordo com a classe Produto
        txtQuantidade.setText(String.valueOf(p.getQtd())); // Muda o texto de acordo com a classe Produto (Converte para string)
        txtValor.setText(String.valueOf(p.getVlr())); // Muda o texto de acordo com a classe Produto (Converte para string)
        txtId.setText(String.valueOf(p.getId())); // Muda o texto de acordo com a classe Produto (Converte para string)
        txtId.setEnabled(false); // Desativa o campo ID (Não pode ser alterado na mão)
        
        this.p = p; // Iguala o produto recebido ao produto criado localmente
        
        this.setLocationRelativeTo(null); // Centralizar o formulário
    }
                         
    private void componentes() {

        btnUpdate = new JButton();
        txtValor = new JTextField(10);
        jLabel3 = new JLabel();
        jLabel2 = new JLabel();
        txtQuantidade = new JTextField(10);
        txtProduto = new JTextField(10);
        jLabel1 = new JLabel();
        txtId = new JTextField(10);
        jLabel4 = new JLabel();

        btnUpdate.setText("Atualizar Cadastro");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel3.setText("Valor");

        jLabel2.setText("Quantidade");

        jLabel1.setText("Produto");

        jLabel4.setText("Index");
        
        // tela
        setTitle("Atualização"); // título da janela
        setSize(205,180);
        setResizable(false);
        
        // container com layout
        Container painel = getContentPane(); // painel
        painel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 8)); // centralizando o layout no meio
        
        // labels e campos texto
        painel.add(jLabel1);
        painel.add(txtProduto);
        painel.add(jLabel2);
        painel.add(txtQuantidade);
        painel.add(jLabel3);
        painel.add(txtValor);
        
        // botões
        painel.add(btnUpdate);
        btnUpdate.setToolTipText("Clique aqui para atualizar o produto modificado");
                        
    }                      

    private void btnUpdateActionPerformed(ActionEvent evt) {                                          
        try
        {
            String produto = txtProduto.getText();
            int qtd = (txtQuantidade.getText().isEmpty() ? 0 : Integer.parseInt(txtQuantidade.getText())); 
            // IF ternário, Se tiver vazio, é 0, se não, é número digitado, convertido de texto do campo txtQuantidade
            double vlr = (txtValor.getText().isEmpty() ? 0.0 : Double.parseDouble(txtValor.getText()));
            // IF ternário, Se tiver vazio, é 0.0, se não, é double digitado, convertido de texto do campo txtValor
            
            if( !produto.isEmpty() && qtd > 0 && vlr > 0.0 ) // Se o produto NÃO estiver vazio, a quantidade for maior que 0, e o valor também
            {
                LocalDate dt = LocalDate.now(); 
                Produto np = new Produto(); // Novo produto
                                
                np.setNome(produto);
                np.setQtd(qtd);
                np.setVlr(vlr);
                np.setDtAdicionado(dt);
                
                Iterator<Produto> it = Produto.hp.iterator();
                            
                while(it.hasNext()) // Loop entre todos os produtos já cadastrados
                {
                    Produto tp = it.next(); // Armazena o produto em TP -> Temporary Product
                    
                    if(tp.equals(p)) // Se o produto temporario for o que estamos editando, ignora
                    {
                        np.setId(p.getId()); // Iguala o ID do antigo ao novo
                        
                        Produto.hp.remove(p); // Remove o antigo
                        Produto.hp.add(np); // Adiciona o novo
                        break; // Quebra o looping
                    }
                }
                
                JOptionPane.showMessageDialog(null, "Produto atualizado"); // Exibe mensagem
            }
        }
        catch(NumberFormatException e){ JOptionPane.showMessageDialog(null, e.getMessage()); } // Exibe o erro, se ocorrer
    }                                         
  
    public static void main(String args[]) {  
        EventQueue.invokeLater(new Runnable() { // eventqueue é uma requisição para abrir uma janela do thread do swing. já que o comando de abrir a janela vem de outra classe, esse é o procedimento padrão
            public void run() {
                new FrameUpdate().setVisible(true);
            }
        });
    }                                                
}
