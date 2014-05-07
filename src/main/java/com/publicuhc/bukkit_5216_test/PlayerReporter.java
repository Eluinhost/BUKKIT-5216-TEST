package com.publicuhc.bukkit_5216_test;

import org.bukkit.entity.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerReporter implements Runnable {

    private final float m_initialExhaustion;
    private final double m_initialHealth;

    private final Player m_player;
    private final Logger m_logger;

    private final boolean m_cancelled;

    public PlayerReporter(Player player, boolean cancelled) {
        m_initialExhaustion = player.getExhaustion();
        m_initialHealth = player.getHealth();
        m_player = player;
        m_logger = player.getServer().getLogger();
        m_cancelled = cancelled;
    }

    @Override
    public void run() {
        m_logger.log(Level.INFO, "C("+m_cancelled+"), H("+m_initialHealth+"=>"+m_player.getHealth()+"), E("+m_initialExhaustion+"=>"+m_player.getExhaustion()+")");
    }
}
