package dev.xkmc.more_wolf_armors.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.xkmc.more_wolf_armors.content.WolfArmorItem;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.WolfArmorLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WolfArmorLayer.class)
public class WolfArmorLayerMixin {

	@Shadow
	@Final
	private WolfModel<Wolf> model;

	@WrapOperation(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/animal/Wolf;FFFFFF)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/WolfArmorLayer;maybeRenderColoredLayer(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/AnimalArmorItem;)V"))
	private void moreWolfArmors$maybeRenderColoredLayer$renderCollar(WolfArmorLayer instance, PoseStack poseStack, MultiBufferSource buffer, int packedLight, ItemStack stack, AnimalArmorItem armorItem, Operation<Void> original, @Local(argsOnly = true) Wolf wolf) {
		if (armorItem instanceof WolfArmorItem) {
			int i = wolf.getCollarColor().getTextureDiffuseColor();
			ResourceLocation tex = armorItem.getOverlayTexture();
			if (tex == null) return;
			model.renderToBuffer(
					poseStack,
					buffer.getBuffer(RenderType.entityCutoutNoCull(tex)),
					packedLight,
					OverlayTexture.NO_OVERLAY,
					FastColor.ARGB32.opaque(i)
			);
		}
	}

}
