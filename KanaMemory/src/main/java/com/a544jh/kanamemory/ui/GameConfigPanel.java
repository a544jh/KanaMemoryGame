/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.a544jh.kanamemory.ui;

import com.a544jh.kanamemory.profile.PlayerProfile;

/**
 *
 * @author axel
 */
public interface GameConfigPanel {
    public void setProfile(PlayerProfile p);
    public void refresh();
    public String getComponentName();
}
