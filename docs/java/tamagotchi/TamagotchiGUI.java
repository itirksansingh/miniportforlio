// TamagotchiGUI.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TamagotchiGUI {
    private JFrame frame;
    private JLabel hungerLabel, energyLabel, happinessLabel, ageLabel, statusLabel;
    private JButton feedButton, playButton, restButton, skipButton;
    private Pet pet;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TamagotchiGUI().createGame());
    }

    public void createGame() {
        String petName = JOptionPane.showInputDialog(null, "Enter your pet's name:", "Name Your Pet", JOptionPane.PLAIN_MESSAGE);

        if (petName == null || petName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pet must have a name!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        pet = new Pet(petName);

        frame = new JFrame("🐣 Tamagotchi Pet GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Top: Stats Panel
        JPanel statsPanel = new JPanel(new GridLayout(5, 1));
        hungerLabel = new JLabel();
        energyLabel = new JLabel();
        happinessLabel = new JLabel();
        ageLabel = new JLabel();
        statusLabel = new JLabel("Take care of your pet!", SwingConstants.CENTER);
        updateLabels();
        statsPanel.add(hungerLabel);
        statsPanel.add(energyLabel);
        statsPanel.add(happinessLabel);
        statsPanel.add(ageLabel);
        statsPanel.add(statusLabel);
        frame.add(statsPanel, BorderLayout.CENTER);

        // Bottom: Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        feedButton = new JButton("Feed 🍗");
        playButton = new JButton("Play 🎮");
        restButton = new JButton("Rest 💤");
        skipButton = new JButton("Do Nothing 😐");
        buttonPanel.add(feedButton);
        buttonPanel.add(playButton);
        buttonPanel.add(restButton);
        buttonPanel.add(skipButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        feedButton.addActionListener(e -> actionPerformed("feed"));
        playButton.addActionListener(e -> actionPerformed("play"));
        restButton.addActionListener(e -> actionPerformed("rest"));
        skipButton.addActionListener(e -> actionPerformed("skip"));

        frame.setVisible(true);
    }

    private void actionPerformed(String action) {
        switch (action) {
            case "feed" -> pet.feed();
            case "play" -> pet.play();
            case "rest" -> pet.rest();
            case "skip" -> statusLabel.setText("⏳ You did nothing. Time passes...");
        }

        pet.decay();
        updateLabels();

        if (!pet.isAlive()) {
            JOptionPane.showMessageDialog(frame, "💀 Oh no... " + pet.getName() + " has passed away...", "Game Over", JOptionPane.ERROR_MESSAGE);
            frame.dispose();
        }
    }

    private void updateLabels() {
        hungerLabel.setText("🍽 Hunger:     " + pet.getHunger() + "/10");
        energyLabel.setText("⚡ Energy:     " + pet.getEnergy() + "/10");
        happinessLabel.setText("😊 Happiness:  " + pet.getHappiness() + "/10");
        ageLabel.setText("🎂 Age:        " + pet.getAge() + " turns");
    }

    // Inner Pet class for quick use
    class Pet {
        private String name;
        private int hunger, energy, happiness, age;
        private boolean alive;

        public Pet(String name) {
            this.name = name;
            hunger = 2;
            energy = 7;
            happiness = 7;
            age = 0;
            alive = true;
        }

        public void feed() {
            hunger = Math.max(hunger - 3, 0);
            statusLabel.setText("🍗 " + name + " enjoyed a nice meal!");
        }

        public void play() {
            happiness = Math.min(happiness + 2, 10);
            energy = Math.max(energy - 2, 0);
            statusLabel.setText("🎮 You played with " + name + "!");
        }

        public void rest() {
            energy = Math.min(energy + 3, 10);
            statusLabel.setText("😴 " + name + " took a nap and feels better!");
        }

        public void decay() {
            hunger = Math.min(hunger + 1, 10);
            happiness = Math.max(happiness - 1, 0);
            energy = Math.max(energy - 1, 0);
            age++;

            if (hunger >= 10 || happiness <= 0 || energy <= 0) {
                alive = false;
            }
        }

        public boolean isAlive() { return alive; }
        public String getName() { return name; }
        public int getHunger() { return hunger; }
        public int getEnergy() { return energy; }
        public int getHappiness() { return happiness; }
        public int getAge() { return age; }
    }
}
