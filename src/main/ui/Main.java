package ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.*;

// Main that initiates console based UI
public class Main {
    public static void main(String[] args) {
//        try {
//            new UfcApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application because the file was not found!");
//        }
        new UfcGUI();
    }
}
