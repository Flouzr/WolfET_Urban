package com.echo;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;

public class ConsoleHandler {

    public static final String CONSOLE_CLASS = "ET Console";
    public static final int CONSOLE_ID = 100;
    public static final int EDIT_ID = 101;

    User32 user32 = User32.INSTANCE;
    WinDef.HWND etHwnd = user32.FindWindowA(null, CONSOLE_CLASS);
    WinDef.HWND consoleHwnd = user32.GetDlgItem(etHwnd, CONSOLE_ID);
    WinDef.HWND editHwnd = user32.GetDlgItem(etHwnd, EDIT_ID);

    interface User32 extends StdCallLibrary {
        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
        int WM_SETTEXT = 0x000C;
        int WM_GETTEXT = 0x000D;
        int WM_GETTEXTLENGTH = 0x000E;
        int WM_KEYDOWN = 0x0100;
        int WM_KEYUP = 0x0101;

        int VK_RETURN = 0x0D;

        WinDef.HWND FindWindowA(String lpClassName, String lpWindowName);
        WinDef.HWND GetDlgItem(WinDef.HWND hDlg, int nIDDlgItem);

        int SendMessageA(WinDef.HWND paramHWND, int paramInt, long l, byte[] lParamStr);
        int SendMessageA(WinDef.HWND paramHWND, int paramInt, int  wParam, int lParam);

        int PostMessageA(WinDef.HWND hWnd, int Msg, int wParam, int lParam);

    }

    public String getConsoleText(int beginIndex, int endIndex){
        byte[] lParamStr = new byte[this.getTextLength() + 1];
        user32.SendMessageA(consoleHwnd, User32.WM_GETTEXT, this.getTextLength() + 1, lParamStr);

        String s = new String(lParamStr);

        try {
            s = s.substring(beginIndex, endIndex);
        }catch (StringIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        return s;
    }

    public int getTextLength(){
        int len = user32.SendMessageA(consoleHwnd, User32.WM_GETTEXTLENGTH, 0, 0);
        return len;
    }

    public void sendText(String text){
        user32.SendMessageA(editHwnd, User32.WM_SETTEXT, 0, Native.toByteArray(text));
        user32.PostMessageA(editHwnd, User32.WM_KEYDOWN, User32.VK_RETURN, 0);
        user32.PostMessageA(editHwnd, User32.WM_KEYUP, User32.VK_RETURN, 0);
    }

}
