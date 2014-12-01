/*
 * Copyright 2014 yarocks. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and contributors and should not be interpreted as representing official policies,
 * either expressed or implied, of anybody else.
 */

package me.yarocks.ai.YarocksCoreLib;/**
 * Created by User Name on 10/24/2014.
 */

import me.yarocks.ai.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by User Name at 11:48 PM on 10/24/2014
 *
 * @author yarocks
 *         © 2014 yarocks
 *         All Rights Reserved.
 */

public class ItemBuilder {
// ? statements:
// oolean statement ? true result : false result;

	public ItemStack is;

	public Main c;

	public ItemBuilder(Main c) {
		this.c = c;
	}

	public ItemBuilder(Material mat) {

		is = new ItemStack(mat);
	}

	/**
	 * Inits the builder with the given {@link ItemStack}
	 *
	 * @param is the {@link ItemStack} to start the builder from
	 * @since 1.0
	 */
	public ItemBuilder(final ItemStack is) {
		this.is = is;
	}


	public ItemBuilder name(String name) {
		final ItemMeta im = is.getItemMeta();

		im.setDisplayName(name);
		is.setItemMeta(im);
		return this;
	}

	public ItemBuilder amt(int amount) {
		is.setAmount(amount);
		return this;
	}

	public ItemBuilder setLore(List<String> lore) {
		final ItemMeta im = is.getItemMeta();

		im.setLore(lore);
		is.setItemMeta(im);
		return this;
	}

	public ItemBuilder addEnchant(Enchantment enchantment, int level) {
		is.addUnsafeEnchantment(enchantment, level);
		return this;
	}


	public ItemBuilder addEnchant(Enchantment enchantment) {
		is.addUnsafeEnchantment(enchantment, 1);
		return this;
	}

	public ItemBuilder addLore(List<String> lore) {
		final ItemMeta im = is.getItemMeta();
		List<String> lore2 = new ArrayList<>();
		if (is.getItemMeta().hasLore()) {
			for (String lore1 : im.getLore()) {
				lore2.add(lore1);
			}
			for (String lore1 : lore) {
				lore2.add(lore1);
			}
		} else {
			for (String lore1 : lore) {
				lore2.add(lore1);
			}
		}
		im.setLore(lore2);
		is.setItemMeta(im);
		return this;
	}

	public ItemBuilder clearLore() {
		final ItemMeta im = is.getItemMeta();

		im.setLore(Arrays.asList(""));
		is.setItemMeta(im);
		return this;
	}

	public ItemBuilder clearEnchantments() {
		for (Enchantment ench : is.getEnchantments().keySet()) {
			is.removeEnchantment(ench);
		}
		return this;
	}

	public ItemBuilder clearEnchant(Enchantment ench) {
		is.removeEnchantment(ench);
		return this;
	}

	public ItemBuilder material(Material type) {
		is.setType(type);
		return this;
	}

	public ItemBuilder item(MaterialData data) {
		is.setData(data);
		return this;
	}

	public ItemStack build() {
		return is;
	}

	public boolean containsLore(ItemStack item, String message) {
		if (item != null) {
			if (item.hasItemMeta()) {
				if (item.getItemMeta().hasLore()) {
					for (String lore : item.getItemMeta().getLore()) {
						if (lore.equals(message)) {
							return true;
						}

					}
				}
			}
		}
		return false;
	}
}