package genetic;

import java.util.Random;

/**
 * Created by Danijel on 9/7/2016.
 */
public interface ICrossover {

    /**
     * It takes the 2 chromosomes and makes two children from those chromosomes by k-points crossover algorithmm.
     * This algorithm firstly generates k indices. Then, it goes from first gene to the last and copies parents genes
     * into the childrens ones. If it gets to the index which number is generated randomly before, it switches which
     * genes will be copied into which children.
     * @param parent1 first parent which is going to be involved in generating children.
     * @param parent2 second parent which is going to be involved in generating children.
     * @param points number of points which will be generated and used in algorithm.
     * @param r random number generator.
     * @return array of children
     * */
    default Chromosome[] kPointsCrossover(Chromosome parent1, Chromosome parent2, int points, Random r) {
        Chromosome[] children = new Chromosome[2];
        int size = parent1.getGenes().length;
        int[] field = new int[size];
        Chromosome child1 = new Chromosome(parent1,false);
        Chromosome child2 = new Chromosome(parent2,false);
        for (int i = 0; i<points; i++) {
            int point = Math.abs(r.nextInt())%size;
            if (field[point] != 1) {
                field[point] = 1;
            } else {
                i--;
            }
        }
        int breakCounter = 0;
        for (int i = 0; i<size; i++) {
            if (field[i] == 1) {
                breakCounter++;
            }
            if (breakCounter % 2 == 0) {
                child1.setGene(parent1.getGeneAt(i),i);
                child2.setGene(parent2.getGeneAt(i),i);
            } else {
                child2.setGene(parent1.getGeneAt(i),i);
                child1.setGene(parent2.getGeneAt(i),i);
            }
        }
        children[0] = child1;
        children[1] = child2;
        return children;
    }

    /**
     * It takes the 2 chromosomes and makes two children from those chromosomes by uniform crossover algorithmm.
     * This algorithm actually processes the following operation : R1 AND R2 OR X AND (R1 XOR R2), where in the first child
     * X is randomly generated array of bytes and in the second that same X, but complemented. R1 is array of bytes(genes) of the
     * first parent, and R2 of the second.
     * @param parent1 first parent which is going to be involved in generating children.
     * @param parent2 second parent which is going to be involved in generating children.
     * @param r random number generator.
     * @return array of children
     * */
    default Chromosome[] uniformCrossover(Chromosome parent1, Chromosome parent2, Random r) {
        Chromosome[] children = new Chromosome[2];
        Chromosome child1 = new Chromosome(parent1,false);
        Chromosome child2 = new Chromosome(parent2,false);
        int size = parent1.getGenes().length;
        byte[] genesRandom = new byte[size];
        for (int i = 0; i<size; i++) {
            if (r.nextInt() % 2 == 0) {
                genesRandom[i] = 0;
            } else {
                genesRandom[i] = 1;
            }
        }
        byte[] genesRandomComplement = complementGenes(genesRandom);
        byte[] genesAND1 = logicAnd(parent1.getGenes(),parent2.getGenes());
        byte[] genesXOR = logicExclusiveOr(parent1.getGenes(),parent2.getGenes());
        byte[] genesANDNormal = logicAnd(genesXOR,genesRandom);
        byte[] genesANDComplement = logicAnd(genesXOR,genesRandomComplement);
        byte[] genesORNormal = logicOr(genesAND1,genesANDNormal);
        byte[] genesORComplement = logicOr(genesAND1,genesANDComplement);
        child1.setGenes(genesORNormal);
        child1.setGenes(genesORComplement);
        children[0] = child1;
        children[1] = child2;
        return children;
    }

    /**
     * It processes the complement operation on the given array of bytes.
     * @param genes1 the array of bytes which is going to be complemented.
     * @return result of logic operation
     * */
    default byte[] complementGenes(byte[] genes1) {
        byte[] genesNew = new byte[genes1.length];
        for (int i = 0; i<genes1.length; i++) {
            if (genes1[i] == 0) {
                genesNew[i] = 1;
            } else {
                genesNew[i] = 0;
            }
        }
        return genesNew;
    }

    /**
     * It processes the OR operation on the given array of bytes.
     * @param genes1 the first array of which is going to be used as an argument in logic operation.
     * @param genes2 the second array of which is going to be used as an argument in logic operation.
     * @return result of logic operation
     * */
    default byte[] logicOr(byte[] genes1, byte[] genes2) {
        byte[] genesNew = new byte[genes1.length];
        for (int i = 0; i<genes1.length; i++) {
            if (genes1[i] == 0 && genes2[i] == 0) {
                genesNew[i] = 0;
            } else {
                genesNew[i] = 1;
            }
        }
        return genesNew;
    }

    /**
     * It processes the AND operation on the given array of bytes.
     * @param genes1 the first array of which is going to be used as an argument in logic operation.
     * @param genes2 the second array of which is going to be used as an argument in logic operation.
     * @return result of logic operation
     * */
    default byte[] logicAnd(byte[] genes1, byte[] genes2) {
        byte[] genesNew = new byte[genes1.length];
        for (int i = 0; i<genes1.length; i++) {
            if (genes1[i] == 0 || genes2[i] == 0) {
                genesNew[i] = 0;
            } else {
                genesNew[i] = 1;
            }
        }
        return genesNew;
    }

    /**
     * It processes the XOR operation on the given array of bytes.
     * @param genes1 the first array of which is going to be used as an argument in logic operation.
     * @param genes2 the second array of which is going to be used as an argument in logic operation.
     * @return result of logic operation
     * */
    default byte[] logicExclusiveOr(byte[] genes1, byte[] genes2) {
        byte[] genesNew = new byte[genes1.length];
        for (int i = 0; i<genes1.length; i++) {
            if ((genes1[i] == 0 && genes2[i] == 0)&&(genes1[i] == 1 && genes2[i] == 1)) {
                genesNew[i] = 0;
            } else {
                genesNew[i] = 1;
            }
        }
        return genesNew;
    }

}
