package com.forte.androidtv.dao;

import com.forte.androidtv.entities.Screen;
import java.util.List;

public interface ScreenDAO {
    public List<Screen> getAllScreens();

    public Long registerScreen(Screen var1);

    public void updateScreen(Long var1, String var2, Long var3, Long var4);

    public void activateScreen(Long var1, String var2);

    public void deleteScreen(Long var1);
}
