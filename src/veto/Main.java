/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Amar
 */
public class Main {
    public static String jdbc="jdbc:sqlite:base/donne.bd";
    
    public Connection connect= null;
    
    
    public Main() throws SQLException {
        connect= DriverManager.getConnection(jdbc);
         initialiser();        
        
    }
    
    public  static Connection getConnection(String jdbc) throws SQLException{
        return DriverManager.getConnection(jdbc);
    }
    
    
    
    private void initialiser() throws SQLException{
        
        //verifier l'existance de la base de donné et les tables sinon elle les creer ; 
        // creation des base donné Clients : 
        String sqlClient="CREATE TABLE IF NOT EXISTS `Clients` (\n" +
"  `idClients` INTEGER  PRIMARY KEY AUTOINCREMENT ,\n" +
"  `nomClient` VARCHAR(45) NOT NULL,\n" +
"  `NumClient` INT NOT NULL,\n" +
"  `prenomClient` VARCHAR(45) NOT NULL,\n" +
"  `adrClient` TEXT NULL,\n" +
"  `Email` VARCHAR(45) NULL,\n" +
"  `dateNaissance` DATE NULL" +
                        ")";
       String sqlRDV="CREATE TABLE IF NOT EXISTS `RDV` (\n" +
"  `idRDV` INTEGER primary key AUTOINCREMENT,\n" +
"  `idClient` INT NOT NULL ,\n" +
"  `idAnimal` INT NULL," +
"  `date` DATE NULL,\n" +
"  `typeRDV` VARCHAR(11) NULL,\n" +
"  `Adresse` TEXT NULL,\n" +
"  FOREIGN KEY(idClient) references Clients(idClients),"
   + "FOREIGN KEY (idAnimal) references Animals(idAnimal))";
       String sqlAnimal="CREATE TABLE IF NOT EXISTS `Animals` (\n" +
"  `idAnimal` INTEGER  NOT NULL primary key AUTOINCREMENT ,\n" +
"  `proprietaire` INT NULL,\n" +
"  `nomAnimal` VARCHAR(45) NULL,\n" +
"  `sexeAnimal` CHAR NULL,\n" +
"  `raceAnimal` VARCHAR(45) NULL,\n" +
"  `especeAnimal` VARCHAR(45) NULL,\n" +
"  `carAnimal` TEXT NULL,\n" +
"  `poids` FLOAT NULL,\n" +
"  `uniteMesure` VARCHAR(2) NULL,\n" +
"    FOREIGN KEY (`proprietaire`)  REFERENCES `Clients` (`idClients`))";
       String sqlMedicament="CREATE TABLE IF NOT EXISTS `Medicament` (\n" +
"  `idMedicament` INTEGER PRIMARY KEY AUTOINCREMENT ,\n" +
"  `nomMedicament` VARCHAR(45) NOT NULL,\n" +
"  `typeMed` VARCHAR(45) NULL)";
       
       String sqlGestion="CREATE TABLE IF NOT EXISTS `gestMedicament` (\n" +
"  `idOperatoin` INTEGER PRIMARY KEY AUTOINCREMENT ,\n" +
"  `idMedicament` INTEGER NOT NULL ,\n" +
"  `qte` INT NOT NULL DEFAULT 0 ,\n" +
"  `prixAchat` FLOAT NOT NULL,\n" +
"  `prixVente` FLOAT NOT NULL,\n" +
"  `dateAchat` DATE NULL,\n" +
"  `dateE` DATE NOT NULL,\n" +
"  `dateF` DATE NOT NULL," +
"   FOREIGN KEY(`idMedicament`) REFERENCES Medicament(idMedicament))";
       String sqlConsultation = " CREATE TABLE IF NOT EXISTS `consultation` ( `idConsultation` INTEGER PRIMARY KEY  NOT NULL,\n" +
"  `idAnimal` INTEGER,\n" +
"  `date` DATETIME NOT NULL ,\n" +
"'heure' DateTime not null,\n" +
"  `notes` TEXT NULL,\n" +
"  `frais` FLOAT  NOT NULL,  \n" +
"`type` varchar(11) not null,\n" +
"    FOREIGN KEY (`idAnimal`)\n" +
"    REFERENCES Animals(`idAnimal`))";
       String sqlCaisse=" create table if not exists caisse("
               + "idcaisse integer,"
               + "somme varchar(11),"
               + "date Date,"
               + "type varchar(1))";
       
        Statement stmt= connect.createStatement();
        
        boolean execute;
        execute = stmt.execute(sqlClient);
        
        
        if(!execute || execute ){
            System.out.println("Creation de la taable Clients .. OK");
            execute=stmt.execute(sqlRDV);
            System.out.println("Creation de la table RDV .. OK");
             execute=stmt.execute(sqlAnimal);
            System.out.println("Creation de la table Animal .. OK");
            execute=stmt.execute(sqlMedicament);
            System.out.println("Creation de la table Medicament .. OK");
            execute=stmt.execute(sqlGestion);
            System.out.println("Creation de la table Gestion des mesdicaments .. OK");
            execute =stmt.execute(sqlConsultation);
            System.out.println("Creation de la table Consultation .. OK");
            execute=stmt.execute(sqlCaisse);
            System.out.println("Creation de la table Caisse .. OK");
            
            
            
            
        }
        

        
        
        
    }
    
    
    
}
