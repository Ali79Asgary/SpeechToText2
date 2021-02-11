package library.assistant.ui.main.toolbar;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.im.InputContext;
import java.util.Locale;

public class PrepareText {

    String text = "";

    public PrepareText() { }

    public PrepareText(String text) { this.text = text; }

    public static void main(String[] args) {
        char[] textChars = divideText("سلام آقا علی");
        for (int i = 0 ; i < textChars.length ; i++){
            persianTextPressKey(textChars[i]);
        }
    }

    public static String detectKeyboardLanguage(){
        InputContext context = InputContext.getInstance();
        System.out.println(context.getLocale().toString());
        return context.getLocale().toString();
    }

    public static char[] divideText(String text){
        String initialText = text;
        int initialTextLength = initialText.length();
        char[] textChars = initialText.toCharArray();
        for (int i = 0 ; i < initialTextLength ; i++){
            System.out.println(textChars[i]);
        }
        return textChars;
    }

    public static void pressKeyboard(char keyChar, String textLanguage){
        try {
            Robot robot = new Robot();
            InputContext inputContext = InputContext.getInstance();
            if(textLanguage.equals("en")){
                if (detectKeyboardLanguage().equals("en_US")){
                    englishTextPressKey(keyChar);
                } else {
                    changeKeyboardLanguage("en");
                    englishTextPressKey(keyChar);
                }
            } else if (textLanguage.equals("fa")){
                if (detectKeyboardLanguage().equals("fa_IR")){
                    persianTextPressKey(keyChar);
                } else {
                    changeKeyboardLanguage("fa");
                    persianTextPressKey(keyChar);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void changeKeyboardLanguage(String language){
        try {
            InputContext inputContext = InputContext.getInstance();
            if (language.equals("en")){
                if (inputContext.selectInputMethod(new Locale("en", "US"))){
                    System.out.println("Keyboard language was changed to english successfully.");
                } else {
                    System.out.println("Keyboard language was not changed.");
                }
            } else if (language.equals("fa")){
                if (inputContext.selectInputMethod(new Locale("fa", "IR"))){
                    System.out.println("Keyboard language was changed to persian successfully.");
                } else {
                    System.out.println("Keyboard language was not changed.");
                }
            } else {
                System.out.println("Your language was not detected.");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void persianTextPressKey(char keyChar){
        try {
            Robot robot = new Robot();
            switch (keyChar){
                //Persian characters start.
                case 'آ':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_H);
                    robot.keyRelease(KeyEvent.VK_H);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'ا':
                    robot.keyPress(KeyEvent.VK_H);
                    robot.keyRelease(KeyEvent.VK_H);
                    break;
                case 'ب':
                    robot.keyPress(KeyEvent.VK_F);
                    robot.keyRelease(KeyEvent.VK_F);
                    break;
                case 'پ':
                    robot.keyPress(KeyEvent.VK_BACK_SLASH);
                    robot.keyRelease(KeyEvent.VK_BACK_SLASH);
                    break;
                case 'ت':
                    robot.keyPress(KeyEvent.VK_J);
                    robot.keyRelease(KeyEvent.VK_J);
                    break;
                case 'ث':
                    robot.keyPress(KeyEvent.VK_E);
                    robot.keyRelease(KeyEvent.VK_E);
                    break;
                case 'ج':
                    robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
                    robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
                    break;
                case 'چ':
                    robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
                    robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
                    break;
                case 'ح':
                    robot.keyPress(KeyEvent.VK_P);
                    robot.keyRelease(KeyEvent.VK_P);
                    break;
                case 'خ':
                    robot.keyPress(KeyEvent.VK_O);
                    robot.keyRelease(KeyEvent.VK_O);
                    break;
                case 'د':
                    robot.keyPress(KeyEvent.VK_N);
                    robot.keyRelease(KeyEvent.VK_N);
                    break;
                case 'ذ':
                    robot.keyPress(KeyEvent.VK_B);
                    robot.keyRelease(KeyEvent.VK_B);
                    break;
                case 'ر':
                    robot.keyPress(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_V);
                    break;
                case 'ز':
                    robot.keyPress(KeyEvent.VK_C);
                    robot.keyRelease(KeyEvent.VK_C);
                    break;
                case 'ژ':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_C);
                    robot.keyRelease(KeyEvent.VK_C);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'س':
                    robot.keyPress(KeyEvent.VK_S);
                    robot.keyRelease(KeyEvent.VK_S);
                    break;
                case 'ش':
                    robot.keyPress(KeyEvent.VK_A);
                    robot.keyRelease(KeyEvent.VK_A);
                    break;
                case 'ص':
                    robot.keyPress(KeyEvent.VK_W);
                    robot.keyRelease(KeyEvent.VK_W);
                    break;
                case 'ض':
                    robot.keyPress(KeyEvent.VK_Q);
                    robot.keyRelease(KeyEvent.VK_Q);
                    break;
                case 'ط':
                    robot.keyPress(KeyEvent.VK_X);
                    robot.keyRelease(KeyEvent.VK_X);
                    break;
                case 'ظ':
                    robot.keyPress(KeyEvent.VK_Z);
                    robot.keyRelease(KeyEvent.VK_Z);
                    break;
                case 'ع':
                    robot.keyPress(KeyEvent.VK_U);
                    robot.keyRelease(KeyEvent.VK_U);
                    break;
                case 'غ':
                    robot.keyPress(KeyEvent.VK_Y);
                    robot.keyRelease(KeyEvent.VK_Y);
                    break;
                case 'ف':
                    robot.keyPress(KeyEvent.VK_T);
                    robot.keyRelease(KeyEvent.VK_T);
                    break;
                case 'ق':
                    robot.keyPress(KeyEvent.VK_R);
                    robot.keyRelease(KeyEvent.VK_R);
                    break;
                case 'ک':
                    robot.keyPress(KeyEvent.VK_SEMICOLON);
                    robot.keyRelease(KeyEvent.VK_SEMICOLON);
                    break;
                case 'گ':
                    robot.keyPress(KeyEvent.VK_QUOTE);
                    robot.keyRelease(KeyEvent.VK_QUOTE);
                    break;
                case 'ل':
                    robot.keyPress(KeyEvent.VK_G);
                    robot.keyRelease(KeyEvent.VK_G);
                    break;
                case 'م':
                    robot.keyPress(KeyEvent.VK_L);
                    robot.keyRelease(KeyEvent.VK_L);
                    break;
                case 'ن':
                    robot.keyPress(KeyEvent.VK_K);
                    robot.keyRelease(KeyEvent.VK_K);
                    break;
                case 'و':
                    robot.keyPress(KeyEvent.VK_COMMA);
                    robot.keyRelease(KeyEvent.VK_COMMA);
                    break;
                case 'ه':
                    robot.keyPress(KeyEvent.VK_I);
                    robot.keyRelease(KeyEvent.VK_I);
                    break;
                case 'ی':
                    robot.keyPress(KeyEvent.VK_D);
                    robot.keyRelease(KeyEvent.VK_D);
                    break;
                //Persian characters end.
                case ' ':
                    robot.keyPress(KeyEvent.VK_SPACE);
                    robot.keyRelease(KeyEvent.VK_SPACE);
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void englishTextPressKey(char keyChar){
        try {
            Robot robot = new Robot();
            switch (keyChar){
                //English characters start.
                case 'A':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_A);
                    robot.keyRelease(KeyEvent.VK_A);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'a':
                    robot.keyPress(KeyEvent.VK_A);
                    robot.keyRelease(KeyEvent.VK_A);
                    break;
                case 'B':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_B);
                    robot.keyRelease(KeyEvent.VK_B);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'b':
                    robot.keyPress(KeyEvent.VK_B);
                    robot.keyRelease(KeyEvent.VK_B);
                    break;
                case 'C':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_C);
                    robot.keyRelease(KeyEvent.VK_C);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'c':
                    robot.keyPress(KeyEvent.VK_C);
                    robot.keyRelease(KeyEvent.VK_C);
                    break;
                case 'D':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_D);
                    robot.keyRelease(KeyEvent.VK_D);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'd':
                    robot.keyPress(KeyEvent.VK_D);
                    robot.keyRelease(KeyEvent.VK_D);
                    break;
                case 'E':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_E);
                    robot.keyRelease(KeyEvent.VK_E);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'e':
                    robot.keyPress(KeyEvent.VK_E);
                    robot.keyRelease(KeyEvent.VK_E);
                    break;
                case 'F':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_F);
                    robot.keyRelease(KeyEvent.VK_F);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'f':
                    robot.keyPress(KeyEvent.VK_F);
                    robot.keyRelease(KeyEvent.VK_F);
                    break;
                case 'G':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_G);
                    robot.keyRelease(KeyEvent.VK_G);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'g':
                    robot.keyPress(KeyEvent.VK_G);
                    robot.keyRelease(KeyEvent.VK_G);
                    break;
                case 'H':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_H);
                    robot.keyRelease(KeyEvent.VK_H);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'h':
                    robot.keyPress(KeyEvent.VK_H);
                    robot.keyRelease(KeyEvent.VK_H);
                    break;
                case 'I':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_I);
                    robot.keyRelease(KeyEvent.VK_I);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'i':
                    robot.keyPress(KeyEvent.VK_I);
                    robot.keyRelease(KeyEvent.VK_I);
                    break;
                case 'J':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_J);
                    robot.keyRelease(KeyEvent.VK_J);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'j':
                    robot.keyPress(KeyEvent.VK_J);
                    robot.keyRelease(KeyEvent.VK_J);
                    break;
                case 'K':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_K);
                    robot.keyRelease(KeyEvent.VK_K);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'k':
                    robot.keyPress(KeyEvent.VK_K);
                    robot.keyRelease(KeyEvent.VK_K);
                    break;
                case 'L':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_L);
                    robot.keyRelease(KeyEvent.VK_L);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'l':
                    robot.keyPress(KeyEvent.VK_L);
                    robot.keyRelease(KeyEvent.VK_L);
                    break;
                case 'M':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_M);
                    robot.keyRelease(KeyEvent.VK_M);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'm':
                    robot.keyPress(KeyEvent.VK_M);
                    robot.keyRelease(KeyEvent.VK_M);
                    break;
                case 'N':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_N);
                    robot.keyRelease(KeyEvent.VK_N);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'n':
                    robot.keyPress(KeyEvent.VK_N);
                    robot.keyRelease(KeyEvent.VK_N);
                    break;
                case 'O':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_O);
                    robot.keyRelease(KeyEvent.VK_O);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'o':
                    robot.keyPress(KeyEvent.VK_O);
                    robot.keyRelease(KeyEvent.VK_O);
                    break;
                case 'P':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_P);
                    robot.keyRelease(KeyEvent.VK_P);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'p':
                    robot.keyPress(KeyEvent.VK_P);
                    robot.keyRelease(KeyEvent.VK_P);
                    break;
                case 'Q':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_Q);
                    robot.keyRelease(KeyEvent.VK_Q);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'q':
                    robot.keyPress(KeyEvent.VK_Q);
                    robot.keyRelease(KeyEvent.VK_Q);
                    break;
                case 'R':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_R);
                    robot.keyRelease(KeyEvent.VK_R);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'r':
                    robot.keyPress(KeyEvent.VK_R);
                    robot.keyRelease(KeyEvent.VK_R);
                    break;
                case 'S':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_S);
                    robot.keyRelease(KeyEvent.VK_S);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 's':
                    robot.keyPress(KeyEvent.VK_S);
                    robot.keyRelease(KeyEvent.VK_S);
                    break;
                case 'T':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_T);
                    robot.keyRelease(KeyEvent.VK_T);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 't':
                    robot.keyPress(KeyEvent.VK_T);
                    robot.keyRelease(KeyEvent.VK_T);
                    break;
                case 'U':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_U);
                    robot.keyRelease(KeyEvent.VK_U);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'u':
                    robot.keyPress(KeyEvent.VK_U);
                    robot.keyRelease(KeyEvent.VK_U);
                    break;
                case 'V':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'v':
                    robot.keyPress(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_V);
                    break;
                case 'W':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_W);
                    robot.keyRelease(KeyEvent.VK_W);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'w':
                    robot.keyPress(KeyEvent.VK_W);
                    robot.keyRelease(KeyEvent.VK_W);
                    break;
                case 'X':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_X);
                    robot.keyRelease(KeyEvent.VK_X);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'x':
                    robot.keyPress(KeyEvent.VK_X);
                    robot.keyRelease(KeyEvent.VK_X);
                    break;
                case 'Y':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_Y);
                    robot.keyRelease(KeyEvent.VK_Y);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'y':
                    robot.keyPress(KeyEvent.VK_Y);
                    robot.keyRelease(KeyEvent.VK_Y);
                    break;
                case 'Z':
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_Z);
                    robot.keyRelease(KeyEvent.VK_Z);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case 'z':
                    robot.keyPress(KeyEvent.VK_Z);
                    robot.keyRelease(KeyEvent.VK_Z);
                    break;
                //English characters end.
                case ' ':
                    robot.keyPress(KeyEvent.VK_SPACE);
                    robot.keyRelease(KeyEvent.VK_SPACE);
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
