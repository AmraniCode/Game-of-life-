/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Cells.HexaCell;
import DisplayPanels.MyHexaCellDisplayPanel;
import DisplayPanels.MyTriangleCellDisplayPanel;
import Interfaces.CELL_TYPE;
import Interfaces.WORLD_TYPE;
import Lookup.PanelLocator;
import Lookup.WorldLocator;
import SuperClass.AbstDisplayPanel;
import Interfaces.EvolveListner;
import Interfaces.ICell;
import SuperClass.AbstWorldGame;
import DisplayPanels.MySquareWorldPanel;
import ToolClass.Parameter;
import Worlds.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author chaki
 */
public class MyAppFrame extends JFrame implements ActionListener{
    private JPanel displayHolder;
    private AbstDisplayPanel displayPanel;
    private Panel contentPanel;
    private Buttons btnCmd;
    public AbstWorldGame world ; 
    private int width;
    private int heigth;
    private double threshold;
    private ParameterFrame parameter ;
    private long threadId =-1;
    EvolveListner listner =null;
    private int squareSize;
    private Parameter params =null;
    public MyAppFrame(String title,Parameter p, ParameterFrame parameter){
        super(title);
        this.dispose();
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        params = p;
        this.threshold = p.threshold;
        this.parameter = parameter;
        this.width = p.width;
        this.heigth =(int)(width +300);
        this.setLocationByPlatform(true);
        setSize(width, heigth);
        
        contentPanel = new Panel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setLayout(new BorderLayout());
        
        setContentPane(contentPanel);
        
        JLabel startingText = new JLabel("START GAME", JLabel.CENTER);
        startingText.setFont(new Font("Serif", Font.PLAIN, 50));        
        
        displayHolder = new JPanel();
        displayHolder.setSize(width,width);
        
        displayHolder.add(startingText,BorderLayout.CENTER);
        contentPanel.add(displayHolder,BorderLayout.CENTER);
        //insert Command buttons
        
        btnCmd = new Buttons(width,(int)(width*0.5));
        contentPanel.add(btnCmd.mainPanel,BorderLayout.SOUTH);
        
        listner = new EvolveListner() {
                     @Override
                     public void OnEvolve(ICell[][] cells) {
                          System.out.println("Evolve in Frame");
                               displayPanel.validate();
                               displayPanel.repaint();
                         }
                 };
        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startEventListners();
        this.setLocationRelativeTo(null);
        //load world that is selected
        loadWorld(p.world,p.cell);

        this.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        //TODO
    }
    
    private void startEventListners(){
        btnCmd.getSettingButton().addActionListener((ActionEvent e) -> {
            this.parameter.setVisible(true);
            this.world.stopWorld();
            this.dispose();
        });
        
        btnCmd.getResetButton().addActionListener((ActionEvent e) -> {
            this.world.reinitialize();
        });
        
         btnCmd.getPauseButton().addActionListener((ActionEvent e) -> {
            world.pause();
        });
        
        btnCmd.getPlayButton().addActionListener((ActionEvent e) -> {
          world.play();
        });
        
        btnCmd.getInsertPatchManuelyButton().addActionListener((ActionEvent e) -> {
            if (!world.getState()){ 

                     btnCmd.getInsertPatchManuelyButton().setText("Insert patch");
                     world.play();
                     btnCmd.getPlayButton().setEnabled(true);
                     btnCmd.getPauseButton().setEnabled(true);

                 }else {

                   btnCmd.getInsertPatchManuelyButton().setText("START");
                   world.insertPatch();
                   btnCmd.getPlayButton().setEnabled(false);
                   btnCmd.getPauseButton().setEnabled(false);
                 }
  
        });
        
        
        
       
    }

    private WORLD_TYPE checkCombination(WORLD_TYPE world_type, CELL_TYPE cell_type){
        System.out.println(world_type);
        if (cell_type == CELL_TYPE.HEXAGONAL)
            world_type =WORLD_TYPE.HEXA_W_CELL;
        if (cell_type == CELL_TYPE.TRIANGLE)
            world_type = WORLD_TYPE.TRIANGLE_W_CELL;
        return world_type;
    }
    public void loadWorld(WORLD_TYPE world_type, CELL_TYPE cell_type){

        try {
            Class.forName("DisplayPanels.MySquareWorldPanel");
            Class.forName("DisplayPanels.MyHexaCellDisplayPanel");
            Class.forName("DisplayPanels.MyTriangleCellDisplayPanel");

            Class.forName("Worlds.MyWorldSquare");
            Class.forName("Worlds.MyTriangleWorld");
            Class.forName("Worlds.MyWorldHexagonal");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (world !=null ){
            this.world.stopWorld();
        }
        world_type = checkCombination(world_type,cell_type);
       // world = WorldLocator.getService(world_type);

        System.out.println(world_type);
        switch (world_type){
            case HEXAGONAL: world=new MyWorldHexagonal();break;
            case SQUARE : world = new MyWorldSquare();break;
            case TRIANGLE: world = new MyTriangleWorld(); break;

            case HEXA_W_CELL: world = new MyHexaCellWorld(); break;
            case TRIANGLE_W_CELL: world = new MyTriangleCellWorld(); break;
            default: world = new MyWorldSquare();
        }



        Properties p = new Properties();
        p.setProperty(AbstWorldGame.WIDTH, Integer.toString(width));
        p.setProperty(AbstWorldGame.HEIGHT, Integer.toString(width));
        p.setProperty(AbstWorldGame.THRESHOLD, String.valueOf(threshold));
        world.initializeWorld(p);
        world.setRefreshRate(params.speed);
        
        
        displayHolder.setVisible(false);
        remove(displayHolder);
        switch (cell_type){
            case HEXAGONAL: displayPanel=new MyHexaCellDisplayPanel();break;
            case SQUARE : displayPanel = new MySquareWorldPanel();break;
            case TRIANGLE: displayPanel = new MyTriangleCellDisplayPanel(); break;
            default: world = new MyWorldSquare();
        }
     //   displayPanel = PanelLocator.getService(cell_type);
      //  displayPanel = new MySquareWorldPanel();
        
     
        squareSize=(int) Math.sqrt(width*width/100);
          
        displayPanel.initPanel(world, width, width,squareSize);
        displayPanel.setPreferredSize(new Dimension(width,width));
        displayPanel.setSize(width,width);
        contentPanel.add(displayPanel,BorderLayout.CENTER);
        System.out.println("donne initialise");
        
        world.subscribeEvent(listner);
        world.startEvent();    
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        
    }
    
    
   
    
    
}
