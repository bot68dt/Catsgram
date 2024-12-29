package ru.practicum;

import info.schnatterer.mobynamesgenerator.MobyNamesGenerator;

import java.util.*;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class Miner {

    private static final int CANDIDATES_COUNT = 345;

    private static final int MINING_CYCLES_COUNT = 54321;

    private final static Logger log = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Miner.class);

    public static void main(String[] args) {
        log.setLevel(Level.INFO);
        log.info("Starting a new JRE coin mining session!");
        Blockchain blockchain = new Blockchain();
        log.trace("Created a blockchain");
        List<Candidate> candidateList = initCandidateList(blockchain, CANDIDATES_COUNT);
        log.trace("Created list of candidates");

        log.info("Running a mining cycle");
        Map<String, Integer> rewards = new HashMap<>();

        for (int cycle = 0; cycle < MINING_CYCLES_COUNT; cycle++){
            log.trace("Starting mining cycle {}", cycle);
            for (Candidate candidate : candidateList) {
                log.trace("Starting candidate try {}", candidate.getName());
                int[] candidateHeader = candidate.getHeader();
                Optional<Blockchain.Block> maybeBlock = blockchain.checkHeader(candidate.getName(), candidateHeader);
                if(maybeBlock.isPresent()){
                    Blockchain.Block block = maybeBlock.get();
                    rewards.put(candidate.getName(), block.getReward() + rewards.getOrDefault(candidate.getName(), 0));
                    log.debug("Candidate {} win a reward", candidate.getName());
                } else {
                    log.debug("Candidate {} did not win", candidate.getName());
                }
            }
        }


        rewards.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(winner -> {
            log.debug("Winner is {} with reward {}",winner.getKey(), winner.getValue());
        });
    }

    private static List<Candidate> initCandidateList(Blockchain blockchain, int count) {
        List<Candidate> list = new ArrayList<>();
        for(int idx = 0; idx < count; idx++){
            list.add(new Candidate(blockchain));
        }
        return list;
    }
}

class Candidate {
    private final static Logger log = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Candidate.class);
    private final Blockchain blockchain;
    private final String name;

    public Candidate(Blockchain blockchain){
        this.name = MobyNamesGenerator.getRandomName();
        this.blockchain = blockchain;
    }

    public int[] getHeader(){
        int[] header = new int[Blockchain.HEADER_LENGTH];
        for (int idx = 0; idx < header.length; idx++){
            int next = (int) Math.min(Math.max(0, Math.round(Math.random() * 10)), 9);
            header[idx] = next;
            log.trace("... {}", next);
        }
        log.trace(";");
        return header;
    }

    public String getName() {
        return name;
    }
}