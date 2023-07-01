package com.example.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Mixins won't work unless they are .java files
// If you want to use mixins put them in this folder and make sure they are .java files
@Mixin(MinecraftServer.class)
public abstract class ExampleMixin {
    @Inject(at = @At("HEAD"), method = "loadWorld")
    private void init(CallbackInfo info) {
        // This code is injected into the start of MinecraftServer.loadWorld()V
    }
}