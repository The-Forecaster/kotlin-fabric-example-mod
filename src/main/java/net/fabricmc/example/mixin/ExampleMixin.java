package net.fabricmc.example.mixin;

import net.fabricmc.example.ExampleMod;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Mixins won't work unless they are .java files
// If you want to use mixins put them in this folder and make sure they are .java files
@Mixin(TitleScreen.class)
public abstract class ExampleMixin {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        ExampleMod.LOGGER.info("This line is printed by an example mod mixin!");
    }
}