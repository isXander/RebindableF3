package dev.isxander.rebindablef3.mixins;

import dev.isxander.rebindablef3.RebindableF3;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(KeyboardHandler.class)
public class KeyboardHandlerMixin {
    @ModifyConstant(method = "keyPress", constant = @Constant(intValue = 292))
    private int modifyDebugKey(int f3Code) {
        return RebindableF3.getF3KeyCode();
    }

    @ModifyVariable(method = "debugFeedbackTranslated", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private Object[] modifyTranslationArgs1(Object[] args) {
        return insertKeyText(args);
    }

    @Redirect(method = "handleDebugKeys", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;translatable(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;"))
    private MutableComponent addHelpTextArg(String translationKey) {
        return Component.translatable(translationKey, RebindableF3.getF3KeyText());
    }

    private Object[] insertKeyText(Object[] args) {
        Object[] newArgs = new Object[args.length + 1];
        newArgs[0] = RebindableF3.getF3KeyText();
        System.arraycopy(args, 0, newArgs, 1, args.length);

        return newArgs;
    }
}
