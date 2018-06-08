package basiccrud;

import java.io.*;
import java.time.*;
import java.util.*;
import javax.swing.*;

public class Produto implements Serializable
{
    public static int count = 0; // Variável que armazena a contagem de produtos, resulta em um ID
    public static HashSet<Produto> hp = new HashSet<Produto>(); // Variável que armazena o HashSet contendo as informações
    public static String path = "C:\\Arquivo.obj"; // Variável que armazena onde o arquivo se localizará no WINDOWS
    
    
    private int id;
    private String nome;
    private int qtd;
    private double vlr;
    private LocalDate dtAdicionado;
    
    public Produto()
    {
        this.id = 0;
        this.nome = null;
        this.qtd = 0;
        this.vlr = 0.0;
        this.dtAdicionado = LocalDate.now();
    }
    
    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getQtd() { return qtd; }
    public void setQtd(int qtd) { this.qtd = qtd; }

    public double getVlr() { return vlr; }
    public void setVlr(double vlr) { this.vlr = vlr; }

    public LocalDate getDtAdicionado() { return dtAdicionado; }
    public void setDtAdicionado(LocalDate dtAdicionado) { this.dtAdicionado = dtAdicionado; }
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
		Produto other = (Produto) obj;
	if (id != other.id)
		return false;
	return true;
    }
        
    /*public static void GravarArquivoTxt(HashSet<Produto> hs, String path)
    {
        File f;
        FileWriter fw;
        BufferedWriter bw;
        
        try
        {
            f = new File(path);
            
            if(!f.exists()) fw = new FileWriter(f);
            else fw = new FileWriter(f, true);
            
            bw = new BufferedWriter(fw);
            
            Iterator<Produto> it = hs.iterator();
            Produto p;
            
            while(it.hasNext())
            {
                p = it.next();
                
                bw.write(p.getId() + "*");
                bw.write(p.getNome() + "*");
                bw.write(p.getQtd() + "*");
                bw.write(p.getVlr() + "*");
                bw.write(String.valueOf(p.getDtAdicionado()) + "\n");
            }
            
            bw.close();
            fw.close();            
        }
        catch(IOException e){ JOptionPane.showMessageDialog(null, e.getMessage()); } // Exibe o erro, se existir
    }
    
    public static HashSet<Produto> LerArquivoTxt(String path)
    {
        HashSet<Produto> hs = new HashSet<>();
        File f;
        BufferedReader br;
        
        try
        {
            f = new File(path);
            br = new BufferedReader(new FileReader(f));
            String linha;
            String campos[];
            Produto p;
            
            while((linha = br.readLine()) != null)
            {
                p = new Produto();
                
                campos = linha.split("\\*"); 
                
                p.setId(Integer.parseInt(campos[0]));
                p.setNome(campos[1]);
                p.setQtd(Integer.parseInt(campos[2]));
                p.setVlr(Double.parseDouble(campos[3]));
                p.setDtAdicionado(LocalDate.parse(campos[4]));
                
                hs.add(p);
            }
        }
        catch(IOException e){ JOptionPane.showMessageDialog(null, e.getMessage()); } // Exibe o erro, se existir
        
        return hs;
    }*/
    
    /*public static void GravarArquivoBinario(HashSet<Produto> hs, String path)
    {
        FileOutputStream fos;
        DataOutputStream dos;
        
        try
        {
            fos = new FileOutputStream(path);   
            dos = new DataOutputStream(fos);
            
            Iterator<Produto> it = hs.iterator();
            Produto p;
            
            while( it.hasNext() )
            {
                p = it.next();
                
                dos.writeInt(p.getId());
                dos.writeUTF(p.getNome());
                dos.writeInt(p.getQtd());
                dos.writeDouble(p.getVlr());
                dos.writeUTF(String.valueOf(p.getDtAdicionado()));
            }
            
            dos.close();
        }
        catch(IOException e){ JOptionPane.showMessageDialog(null, e.getMessage()); } // Exibe o erro, se existir
    }
    
    public static HashSet<Produto> LerArquivoBinario(String path)
    {
        FileInputStream fis;
        DataInputStream dis;
        HashSet<Produto> hs = new HashSet<>();
        
        try
        {
            fis = new FileInputStream(path);
            dis = new DataInputStream(fis);
            Produto p;
            
            while(dis.available() > 0)
            {
                p = new Produto();
                
                p.setId(dis.readInt());
                p.setNome(dis.readUTF());
                p.setQtd(dis.readInt());
                p.setVlr(dis.readDouble());
                p.setDtAdicionado(LocalDate.parse(dis.readUTF()));
                
                hs.add(p);
            }
        }
        catch(IOException | NullPointerException e){ JOptionPane.showMessageDialog(null, e.getMessage()); } // Exibe o erro, se existir
        
        return hs;
    }*/
    
    public static void GravarArquivoObj(HashSet<Produto> hs, String path) 
    {
        FileOutputStream arq; 
        ObjectOutputStream oos;
        
        try
        {            
            arq = new FileOutputStream(path); // Abre o arquivo determinado em PATH
            oos = new ObjectOutputStream(arq); // Abre o fluxo de dados
            
            oos.writeObject(hs); // Armazena no arquivo o HashSet inteiro
            oos.close(); // Fecha o fluxo de dados
        }
        catch(IOException e){ JOptionPane.showMessageDialog(null, e.getMessage()); } // Exibe o erro, se existir
    }
    
    public static HashSet<Produto> LerArquivoObj(String path)
    {
        FileInputStream fis;
        ObjectInputStream ois;
        HashSet<Produto> hs = new HashSet<>();
        
        try
        {            
            fis = new FileInputStream(path); // Abre o arquivo determinado em PATH
            ois = new ObjectInputStream(fis); // Abre o fluxo de dados
            
            hs = (HashSet<Produto>) ois.readObject(); // Converte o que vem do arquivo para HashSet e armazena na variável estática
        }
        catch(IOException | ClassNotFoundException e){ JOptionPane.showMessageDialog(null, e.getMessage()); } // Exibe o erro, se existir
        
        return hs;
    }
}

    
 

