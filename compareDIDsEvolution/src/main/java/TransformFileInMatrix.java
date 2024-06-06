import javax.swing.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransformFileInMatrix {
    int columsOld;
    int rowOld;
    int columsNew;
    int rowNew;

    String[][] matrixOld;
    String[][] matrixNew;
    Map<String, ArrayList<ArrayList>> mapWithChangesOnExistInOldAndStillInNew;

    public TransformFileInMatrix(String path1, String path2){
        //open first excel and move all cels in array of Strings
        String filePathOld = path1;
        ReadFromFiles fileOld = new ReadFromFiles(filePathOld,0);
         columsOld = fileOld.getColumsCount();
         rowOld = fileOld.getRowCount();
        matrixOld = new String[rowOld][columsOld];
        for (int i = 0; i < rowOld;i++) {
            for(int j = 0; j < columsOld; j++) {

                try {
                    matrixOld[i][j] = fileOld.getCellData(i,j).toString();
                    System.out.println(" " + matrixOld[i][j] +" ");
                } catch (NullPointerException e) {
                    matrixOld[i][j] = "-";
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println(i + " " +j);
                    JOptionPane.showMessageDialog(null,e);
                }
            }
        }



        //open second excel and move all cels in array of Strings
        String filePathNew = path2;
        ReadFromFiles fileNew = new ReadFromFiles(filePathNew,0);
        columsNew =fileNew.getColumsCount();
        rowNew = fileNew.getRowCount();
        matrixNew = new String[rowNew][columsOld];
        for (int i = 0; i < rowNew;i++) {
            for(int j = 0; j < columsNew; j++) {
                try {
                    matrixNew[i][j] = fileNew.getCellData(i, j).toString();
                }  catch (NullPointerException e) {
                    matrixNew[i][j] = "-";
                } catch (ArrayIndexOutOfBoundsException e){
                    matrixNew[i][j] = "-";
                    System.out.println(i + " " +j);
                    JOptionPane.showMessageDialog(null,e);
                }
               // System.out.println();
            }
        }
    }

    //Evolution of new excel file take in to a count only the first columns as a key
    public static String[][] didNewInEvolution(String [][] newEvolution,int rowsNew,int columsNew,  String [][] oldEvolution,int rowsOld,int columnsOld){
        boolean isInOldEvolution = false;
        int countLineForNewMatrix = 0;

        for (int i = 0; i < rowsNew; i++){
            isInOldEvolution = false;
            for (int j = 0; j < rowsOld; j++){
                if (newEvolution[i][0].equalsIgnoreCase(oldEvolution[j][0])){
                    isInOldEvolution = true;
                }
            }
            if (isInOldEvolution == false){
                countLineForNewMatrix++;
            }
        }

        String[][] matrixNewDidInEvolution = new String[countLineForNewMatrix][columsNew];
        countLineForNewMatrix = 0;

        for (int i = 0; i < rowsNew; i++){
            isInOldEvolution = false;
            for (int j = 0; j < rowsOld; j++){
                if (newEvolution[i][0].equalsIgnoreCase(oldEvolution[j][0])){
                    isInOldEvolution = true;
                }
            }
            if (isInOldEvolution == false){
                for (int k = 0; k < columsNew; k++) {
                    matrixNewDidInEvolution[countLineForNewMatrix][k] = newEvolution[i][k];
                }
                countLineForNewMatrix++;
            }
        }
        return matrixNewDidInEvolution;
    }


    //what not appear in new excel file take in to a count only the first columns as a key
    public static String[][] didDeletInEvolution(String [][] newEvolution,int rowsNew,int columsNew,  String [][] oldEvolution,int rowsOld,int columnsOld){
        boolean isInNewEvolution = false;
        int countLineForNewMatrix = 0;

        for (int i = 0; i < rowsOld; i++){
            isInNewEvolution = false;
            for (int j = 0; j < rowsNew; j++){
                if (oldEvolution[i][0].equalsIgnoreCase(newEvolution[j][0])){
                    isInNewEvolution = true;
                }
            }
            if (isInNewEvolution == false){
                countLineForNewMatrix++;
            }
        }

        String[][] matrixOldDidDeletedInEvolution = new String[countLineForNewMatrix][columnsOld];
        countLineForNewMatrix = 0;

        for (int i = 0; i < rowsOld; i++){
            isInNewEvolution = false;
            for (int j = 0; j < rowsNew; j++){
                if (oldEvolution[i][0].equalsIgnoreCase(newEvolution[j][0])){
                    isInNewEvolution = true;
                }
            }
            if (isInNewEvolution == false){
                for (int k = 0; k < columsNew; k++) {
                    matrixOldDidDeletedInEvolution[countLineForNewMatrix][k] = oldEvolution[i][k];
                }
                countLineForNewMatrix++;
            }
        }
        return matrixOldDidDeletedInEvolution;
    }

    //what is changed in all cells in  new excel file for all commons, take in to a count only the first columns as a key commons


    public void printAll(){

        WriteResultInExcelFile w = new WriteResultInExcelFile();

        String[][] matrixNewEvolution;
        matrixNewEvolution = didNewInEvolution(matrixNew, rowNew,columsNew,matrixOld,rowOld,columsOld);

        String[][] matrixOldEvolution;
        matrixOldEvolution = didDeletInEvolution(matrixNew, rowNew,columsNew,matrixOld,rowOld,columsOld);

       // String[][] matrix = null;
       // matrix = changedInCellForAllCommonDids(matrixNew, rowNew,columsNew,matrixOld,rowOld,columsOld);

        changeArraysInMaps(matrixNew, rowNew,columsNew,matrixOld,rowOld,columsOld);
         w.writeInExcelFile(matrixNew,matrixNewEvolution,matrixOldEvolution,mapWithChangesOnExistInOldAndStillInNew);


    }


    public  void changeArraysInMaps(String [][] newEvolution,int rowsNew,int columsNew,  String [][] oldEvolution,int rowsOld,int columnsOld) {

        Map<String, ArrayList<ArrayList>> mapNewMatrix = new HashMap<>();
        Map<String,ArrayList<ArrayList>> mapOldmatrix = new HashMap<>();




        for (int i = 0; i < rowsNew ; i++ ) {
            String key = newEvolution[i][0]; // Cheia este prima coloană
            if (mapNewMatrix.containsKey(key)){
                ArrayList<ArrayList> existingList = mapNewMatrix.get(key);
                ArrayList<String> newList = new ArrayList<>();

                // Adăugăm elementele din noua evoluție
                for (int j = 0; j < columsNew; j++) {
                    newList.add(newEvolution[i][j]);
                }

                // Actualizăm lista existentă
                existingList.add(newList);
                mapNewMatrix.put(key, existingList);
            } else {
                // Dacă cheia nu există, creăm o nouă listă și o adăugăm în map
                ArrayList<ArrayList> newList = new ArrayList<>();
                ArrayList<String> temp = new ArrayList<>();

                // Adăugăm elementele din noua evoluție
                for (int j = 0; j < columsNew; j++) {
                    temp.add(newEvolution[i][j]);
                }
                newList.add(temp);
                mapNewMatrix.put(key,newList);
            }
        }


        // old files in a map file

        for (int i = 0; i < rowsOld ; i++ ) {
            String key = oldEvolution[i][0]; // Cheia este prima coloană
            if (mapOldmatrix.containsKey(key)){
                ArrayList<ArrayList> existingList = mapOldmatrix.get(key);
                ArrayList<String> newList = new ArrayList<>();

                // Adăugăm elementele din noua evoluție
                for (int j = 0; j < columnsOld; j++) {
                    newList.add(oldEvolution[i][j]);
                }

                // Actualizăm lista existentă
                existingList.add(newList);
                mapOldmatrix.put(key, existingList);
            } else {
                // Dacă cheia nu există, creăm o nouă listă și o adăugăm în map
                ArrayList<ArrayList> newList = new ArrayList<>();
                ArrayList<String> temp = new ArrayList<>();

                // Adăugăm elementele din noua evoluție
                for (int j = 0; j < columnsOld; j++) {
                    temp.add(oldEvolution[i][j]);
                }
                newList.add(temp);
                mapOldmatrix.put(key,newList);
            }
        }

        // Map pentru elementele diferite
        Map<String, ArrayList<ArrayList>> mapDifferent = new HashMap<>();

// Parcurgem mapOldmatrix și verificăm fiecare cheie și listă asociată
        for (Map.Entry<String, ArrayList<ArrayList>> entryNew : mapNewMatrix.entrySet()) {
            String key = entryNew.getKey();
            ArrayList<ArrayList> listNew = entryNew.getValue();
            boolean isTrue = false;

            // Verificăm dacă cheia din mapOldmatrix există și în mapNewMatrix
            if (mapOldmatrix.containsKey(key)) {
                ArrayList<ArrayList> listOld = mapOldmatrix.get(key);
                ArrayList<ArrayList> newList = new ArrayList<>();
                int lastList = 0;
                // Comparăm listele asociate cheii în cele două map-uri
                int size = Math.min(listNew.size(),listOld.size());
                for (int i = 0; i < size; i++) {

                    ArrayList<String> rowOld = listOld.get(i);
                    ArrayList<String> rowNew = listNew.get(i);
                    ArrayList<String> newRow = new ArrayList<>();

                    int count = 0;
                    lastList = i;
                    // Parcurgem fiecare element din listă și comparăm valorile
                    for (int j = 1; j < rowNew.size(); j++) {

                            String valueOld = rowOld.get(j);
                            String valueNew = rowNew.get(j);


                        // Comparăm valorile
                        if (!valueNew.equals(valueOld)) {
                            newRow.add(valueNew);
                            count++;
                            isTrue = true;
                        } else {
                            newRow.add("-");
                        }
                    }
                    if (count != 0){
                        newList.add(newRow);
                    }


                }


                if (listNew.size() > listOld.size()){
                    ArrayList<String> rowNew = listNew.get(lastList + 1);
                    ArrayList<String> newRow = new ArrayList<>();
                    for (int k = 1; k < rowNew.size(); k++){
                        String valueNew = rowNew.get(k);
                        newRow.add(valueNew);
                    }
                    newList.add(newRow);
                    }

                if (isTrue) {
                    mapDifferent.put(key, newList);
                }

            }
        }

        mapWithChangesOnExistInOldAndStillInNew = mapDifferent;
    }

}
