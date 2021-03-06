package ui;

import model.Names;
import model.UfcWorld;
import persistence.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;

// Main that initiates console based UI
public class Main {
    public static void main(String[] args) {
        try {
            new UfcApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application because the file was not found!");
        }
    }
}
