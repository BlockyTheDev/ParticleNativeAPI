package com.github.fierioziy.particlenativeapi.plugin;

import com.github.fierioziy.particlenativeapi.api.ParticleNativeAPI;
import com.github.fierioziy.particlenativeapi.core.ParticleNativeCore;
import com.github.fierioziy.particlenativeapi.api.utils.ParticleException;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * <p>A main <code>JavaPlugin</code> instance.</p>
 *
 * <p>It is responsible for providing class implementation for
 * particle type list. If any error occurs during class
 * generation, a <code>isValid</code> method will return false
 * and any API access to this instance will
 * throw <code>ParticleException</code>.</p>
 *
 * <p>Therefore, before accessing any particles list you must
 * check, if plugin API is valid
 * by a <code>isValid</code> method call.</p>
 */
public class ParticleNativePlugin extends JavaPlugin {

    private static ParticleNativePlugin INSTANCE = null;

    private boolean isValid = false;

    /**
     * <p>Dynamically generated API implementation.</p>
     */
    private ParticleNativeAPI api;

    @Override
    public void onLoad() {
        INSTANCE = this;

        api = null;
        isValid = false;

        try {
            //try to generate API
            api = ParticleNativeCore.loadAPI(this);
            isValid = true;
        } catch (ParticleException e) {
            getLogger().severe("Failed to dynamically create class library using ASM.");
            getLogger().severe("This API might not be compatible with current server version.");
            getLogger().severe("If you suspect it is a bug, report it on issue tracker on plugin's github page with provided stacktrace:");
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        if (!isValid) {
            this.setEnabled(false);
        }
    }

    @Override
    public void onDisable() {
        INSTANCE = null;

        api = null;
        isValid = false;
    }

    /**
     * <p>Gets this plugin's instance</p>
     * @return a <code>ParticleNativePlugin</code> instance.
     */
    public static ParticleNativePlugin getPlugin() {
        return INSTANCE;
    }

    /**
     * <p>Gets underlying particle API generated by a plugin.</p>
     * @return a <code>ParticleNativeAPI</code> instance.
     * @throws ParticleException if error occurred during class generation.
     */
    public static ParticleNativeAPI getAPI() {
        if (!INSTANCE.isValid) {
            throw new ParticleException("Tried to access API in invalid state.");
        }
        return INSTANCE.api;
    }

    /**
     * <p>Checks if an API has been properly generated and
     * is ready for use.</p>
     *
     * @return true if API has been successfully created, false otherwise.
     */
    public static boolean isValid() {
        return INSTANCE.isValid;
    }

}
