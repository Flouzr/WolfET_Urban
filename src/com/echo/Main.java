package com.echo;

public class Main {

    public static void main(String[] args) {
        ConsoleHandler handler = new ConsoleHandler();

        int previousLength = 0, newLength = 0;

        //handler.sendText("test");

            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                newLength = handler.getTextLength();

                if(newLength < 0){
                    newLength = 0;
                    previousLength = 0;
                }else if (newLength != previousLength) {
                    String word = handler.getConsoleText(previousLength, newLength);
                    if(word.contains("!ud")){
                        word = word.substring(word.indexOf("!ud") + 3, word.indexOf("\r\n")).trim();
                        System.out.println("Found word: " + word);
                    }
                    previousLength = newLength;
                }
            }
    }
}
