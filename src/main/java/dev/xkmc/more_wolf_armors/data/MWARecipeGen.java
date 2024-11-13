package dev.xkmc.more_wolf_armors.data;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import dev.xkmc.more_wolf_armors.init.MWAItems;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.BiFunction;

public class MWARecipeGen {

	public static void genRecipes(RegistrateRecipeProvider pvd) {
		unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MWAItems.TEMPLATE)::unlockedBy, Items.ARMADILLO_SCUTE)
				.pattern("BBB").pattern("BSB").pattern("III")
				.define('B', Items.BONE_BLOCK)
				.define('I', Items.BONE)
				.define('S', Items.ARMADILLO_SCUTE)
				.save(pvd);

		unlock(pvd, SmithingTransformRecipeBuilder.smithing(
				Ingredient.of(MWAItems.TEMPLATE),
				Ingredient.of(Items.WOLF_ARMOR),
				Ingredient.of(Items.IRON_BLOCK),
				RecipeCategory.COMBAT,
				MWAItems.IRON.get())::unlocks, MWAItems.TEMPLATE.get())
				.save(pvd, MWAItems.IRON.getId());
	}

	public static <T> T unlock(RegistrateRecipeProvider pvd, BiFunction<String, Criterion<InventoryChangeTrigger.TriggerInstance>, T> func, Item item) {
		return func.apply("has_" + pvd.safeName(item), DataIngredient.items(item).getCriterion(pvd));
	}


}
