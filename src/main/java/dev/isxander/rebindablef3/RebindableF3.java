package dev.isxander.rebindablef3;

import com.mojang.blaze3d.platform.InputConstants;
import dev.isxander.rebindablef3.mixins.KeyMappingAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;

public class RebindableF3 implements ClientModInitializer {
    private static KeyMapping keyBinding = null;

    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(keyBinding = new KeyMapping("key.rebindablef3.debug", InputConstants.KEY_F3, "key.categories.misc"));
    }

    public static int getF3KeyCode() {
        if (keyBinding == null)
            return 292;
        return ((KeyMappingAccessor) keyBinding).getKey().getValue();
    }

    public static Component getF3KeyText() {
        if (keyBinding == null)
            return Component.translatable("key.keyboard.f3");
        return keyBinding.getTranslatedKeyMessage();
    }
}
