package basiccrud;

import java.io.*;
import java.util.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class FrameCRUD extends JFrame {
    
    private JButton btnCreate;
    private JButton btnDelete;
    private JButton btnRead;
    private JButton btnUpdate;
    
    public FrameCRUD() {
        componentes(); // Inicia componentes
                     
        this.setLocationRelativeTo(null); // Centraliza o formulário
        
        JOptionPane.showMessageDialog(null, "!===O DIRETORIO DE GRAVAÇÃO DO ARQUIVO ESTÁ PADRONIZADO PARA SALVAR NO -> C:\\===!");
        
        File f = new File(Produto.path);
        
        if( f.exists())// Se o arquivo existir
        {
            Produto.hp = Produto.LerArquivoObj(Produto.path);// Leia o arquivo
            //Produto.hp = Produto.LerArquivoTxt(Produto.path);// Leia o arquivo
            //Produto.hp = Produto.LerArquivoBinario(Produto.path);// Leia o Arquivo
        }
    }
                         
    private void componentes() {

        btnCreate = new JButton();
        btnRead = new JButton();
        btnUpdate = new JButton();
        btnDelete = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                
        btnCreate.setText("Criar Produto");
        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnRead.setText("Buscar Produto");
        btnRead.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnReadActionPerformed(evt);
            }
        });

        btnUpdate.setText("Atualizar Produto");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Excluir Produto");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent evt)
            {
                Produto.GravarArquivoObj(Produto.hp, Produto.path); // Salva o arquivo ao fechar a aplicão
                //Produto.GravarArquivoTxt(Produto.hp, Produto.path); // Salva o arquivo ao fechar a aplicão
                //Produto.GravarArquivoBinario(Produto.hp, Produto.path); // Salva o arquivo ao fechar a aplicão
            }
        });
        
        // tela
        setTitle("Estoque"); // título da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // saindo da aplicação após fechar
        setSize(250,180);
        setResizable(false);
        
        // container com layout
        Container painel = getContentPane(); // painel
        painel.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8));
        
        // botões
        painel.add(btnCreate);
        painel.add(btnRead);
        painel.add(btnUpdate);
        painel.add(btnDelete);
        btnCreate.setToolTipText("Clique aqui para cadastrar um produto");
        btnRead.setToolTipText("Clique aqui para buscar um produto");
        btnUpdate.setToolTipText("Clique aqui para atualizar um produto");
        btnDelete.setToolTipText("Clique aqui para excluir um produto"); 
    }

    private void btnCreateActionPerformed(ActionEvent evt) {                                          
        // Apenas exibindo o formulário de criação
        FrameCreate fc = new FrameCreate();
        fc.setVisible(true);
    }                                         

    private void btnReadActionPerformed(ActionEvent evt) {                                        
        if(Produto.hp.size() > 0) // Se tiver cadastros
        {        
            FrameRead fc = new FrameRead(); // Instancio o formulário
            fc.setVisible(true); // Exibe o formulário
        }
        else JOptionPane.showMessageDialog(null, "Você ainda não cadastrou nada"); // Exibe mensagem
    }                                       

    private void btnUpdateActionPerformed(ActionEvent evt) {                                          
        try
        {
            if(Produto.hp.size() > 0) // Se tiver cadastros
            {            
                int op = -1; // Crio a variável que armazena a opção digitada, -1 aqui é só um valor simbólico para o WHILE iniciar ( < 0 e > 4 )

                while(op < 0 || op > 4)
                {
                    // Variável que armazena a opção digitada
                    op = Integer.parseInt
                        (
                            JOptionPane.showInputDialog
                            (
                                null, 
                                "Procurar por qual elemento?\n"
                                + "1 - Produto\n"
                                + "2 - Quantidade\n"
                                + "3 - Valor\n"
                                + "4 - Index\n"
                                + "0 - Sair"
                            )
                        );

                    Iterator<Produto> it = Produto.hp.iterator(); // Iterador padrão

                    Produto result = new Produto(); // Objeto que servirá como FLAG para saber se encontrou e também
                    // para passar como parâmetro para o update

                    switch(op)
                    {
                        case 0:{ break; }
                        case 1:
                        {
                            String nome = JOptionPane.showInputDialog(null, "Digite o nome"); // Armazena o nome digitado

                            while(it.hasNext()) // Enquanto tiver cadastros
                            {
                                Produto p = it.next(); // Armazena o produto da iteração atual

                                if(p.getNome().equals(nome)) // Achei o nome
                                {
                                    result = p; // Armazeno o objeto

                                    break; // Quebro o while
                                }
                            }

                            if( result.getNome() != null ) // Se tiver achado o objeto (O nome aqui é só pra indicar, podia ser qualquer um)
                            {
                                FrameUpdate fu = new FrameUpdate(result); // Passo o objeto que eu achei como parâmetro
                                fu.setVisible(true); // Exibe o formulário de UPDATE "Atualizar produto"
                            }
                            else JOptionPane.showMessageDialog(null, "Não encontrei");

                            break;
                        }
                        case 2: // Se tiver EMPATE, ele pega o primeiro
                        {
                            int qtd = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade")); // Armazena a quantidade

                            while(it.hasNext()) // Enquanto tiver cadastros
                            {
                                Produto p = it.next(); // Armazena o produto da iteração atual

                                if(p.getQtd() == qtd) // Achei a quantidade
                                {
                                    result = p; // Armazeno o objeto

                                    break; // Quebro o while
                                }
                            }

                            if( result.getNome() != null ) // Se tiver achado o objeto (O nome aqui é só pra indicar, podia ser qualquer um)
                            {
                                FrameUpdate fu = new FrameUpdate(result); // Passo o objeto que eu achei como parâmetro
                                fu.setVisible(true); // Exibe o formulário de UPDATE
                            }
                            else JOptionPane.showMessageDialog(null, "Não encontrei"); // Exibe mensagem

                            break;
                        }
                        case 3: // Se tiver EMPATE, ele pega o primeiro
                        {
                            double vlr = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor")); // Armazena o valor

                            while(it.hasNext()) // Enquanto tiver cadastros
                            {
                                Produto p = it.next(); // Armazena o produto da iteração atual

                                if(p.getVlr() == vlr) // Achei a quantidade
                                {
                                    result = p; // Armazeno o objeto

                                    break; // Quebro o while
                                }
                            }

                            if( result.getNome() != null ) // Se tiver achado o objeto (O nome aqui é só pra indicar, podia ser qualquer um)
                            {
                                FrameUpdate fu = new FrameUpdate(result); // Passo o objeto que eu achei como parâmetro
                                fu.setVisible(true); // Exibe o formulário de UPDATE
                            }
                            else JOptionPane.showMessageDialog(null, "Não encontrei"); // Exibe mensagem

                            break;
                        }
                        case 4:
                        {
                            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id")); // Armazena o index

                            while(it.hasNext()) // Enquanto tiver cadastros
                            {
                                Produto p = it.next(); // Variável de produto que armazena a iteração atual

                                if(p.getId() == id) // Achei o index
                                {
                                    result = p; // Armazeno o objeto

                                    break; // Quebro o while
                                }
                            }

                            if( result.getNome() != null ) // Se tiver achado o objeto (O nome aqui é só pra indicar, podia ser qualquer um)
                            {
                                FrameUpdate fu = new FrameUpdate(result); // Passo o objeto que eu achei como parâmetro
                                fu.setVisible(true); // Exibe o formulário de UPDATE
                            }
                            else JOptionPane.showMessageDialog(null, "Não encontrei"); // Exibe mensagem

                            break;
                        }
                        default:
                        {
                            JOptionPane.showMessageDialog(null, "Valor inválido"); // Exibe mensagem
                            
                            break;
                        }
                    }
                }
            }
            else JOptionPane.showMessageDialog(null, "Você ainda não cadastrou nada"); // Exibe mensagem
        }
        catch(NumberFormatException e){ JOptionPane.showMessageDialog(null, e.getMessage()); } // Exibe erro, se ocorrer    
    }                                         

    private void btnDeleteActionPerformed(ActionEvent evt) {                                          
        try
        {
            if(Produto.hp.size() > 0)
            {            
                int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o index")); // Recebe o index que será removido

                if(id > 0) // Se o ID for positivo
                {
                    Iterator<Produto> it = Produto.hp.iterator(); // Iterador normal do produto

                    Produto tp; // Variável que guarda o produto temporariamente
                    int index = 0; // Utilizado como flag e identificador para o id encontrado (ou não)
                    // Se o index continuar 0, significa que não achou o id
                    // Se mudar pra qualquer outro número, significa que achou e então tem que alterar o ID dos subsequentes

                    while(it.hasNext()) // Enquanto tiver mais cadastros
                    {
                        tp = it.next(); // Armazena o cadastro atual na variável temporária de produto

                        if(tp.getId() == id) // Se o ID digitado for igual ao ID da classe
                        {
                            JOptionPane.showMessageDialog(null, "Produto removido com sucesso"); // Exibe mensagem

                            index = id; // Salva o ID nesta variável de FLAG e IDENTIFICAÇÃO

                            Produto.hp.remove(tp); // Remove o produto
                            
                            break; // Quebra o laço WHILE pois não precisamos mais procurar
                        }
                    }
                    
                    /*if( index > 0 ) // ESSE SISTEMA ABAIXA O ID DOS PRODUTOS SUBSEQUENTES SE ENCONTRADO O ID
                    {
                        Iterator<Produto> itCorrige = Produto.hp.iterator(); // Outro iterador
                        Produto tpCorrige; // Outra variável
                        
                        HashSet<Produto> newHashSet = new HashSet<>(); // Aqui criamos um novo HashSet
                        // Isso é necessário para igualar ao HashSet da classe produto, que é o que guarda tudo
                            
                        while(itCorrige.hasNext()) // Enquanto tiver cadastros
                        {
                            tpCorrige = itCorrige.next(); // Armazena o cadastro atual na variável de correção
                               
                            if(tpCorrige.getId() > id) // Se o ID for maior que o ID que nós removemos
                                tpCorrige.setId(tpCorrige.getId() - 1); // Nós atualizamos os IDs subsequentes, diminuimos 1 em todos eles
                                
                            newHashSet.add(tpCorrige); // Independente de ser maior ou menor o ID, nós ainda armazenamos o produto
                            // Pois ele será igualado ao HashSet da classe produto, que é o que guarda tudo
                        }
                            
                        Produto.hp = newHashSet; // Igualamos ao oficial da classe produto
                        
                        // Tudo isso foi feito para que ao utilizar Produto.hp.size() ele não retorne um valor que gere IDs iguais
                        // Eu tentei fazer ali em cima sem a variável index direto, mas não é possível pois o java gera uma exceção de coleção
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Não encontrei esse index"); // Exibe mensagem*/
                }
            }
            else JOptionPane.showMessageDialog(null, "Você ainda não cadastrou nada"); // Exibe mensagem
        }
        catch(NumberFormatException e){ JOptionPane.showMessageDialog(null, e.getMessage()); } // Exibe erro, se existir
    }                          

    public static void main(String args[]) {     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameCRUD().setVisible(true);
            }
        });        
    }                                            
}
