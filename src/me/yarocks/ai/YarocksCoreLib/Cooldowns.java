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
 * Created by User Name on 11/3/2014.
 */

/**
 * Created by User Name at 5:51 PM on 11/3/2014
 *
 * @author yarocks
 *         © 2014 yarocks
 *         All Rights Reserved.
 */

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.bukkit.entity.Player;


public class Cooldowns {
	private static Table<String, String, Long> cooldowns = HashBasedTable.create();

	/**
	 * Retrieve the number of milliseconds left until a given cooldown expires.
	 * <p/>
	 * Check for a negative value to determine if a given cooldown has expired. <br>
	 * Cooldowns that have never been defined will return {@link Long#MIN_VALUE}.
	 *
	 * @param player - the player.
	 * @param key    - cooldown to locate.
	 * @return Number of milliseconds until the cooldown expires.
	 */
	public static long getCooldown(Player player, String key) {
		return calculateRemainder(cooldowns.get(player.getName(), key));
	}

	/**
	 * Update a cooldown for the specified player.
	 *
	 * @param player - the player.
	 * @param key    - cooldown to update.
	 * @param delay  - number of milliseconds until the cooldown will expire again.
	 * @return The previous number of milliseconds until expiration.
	 */
	public static long setCooldown(Player player, String key, long delay) {
		return calculateRemainder(
				cooldowns.put(player.getName(), key, System.currentTimeMillis() + delay));
	}

	/**
	 * Determine if a given cooldown has expired. If it has, refresh the cooldown. If not, do nothing.
	 *
	 * @param player - the player.
	 * @param key    - cooldown to update.
	 * @param delay  - number of milliseconds until the cooldown will expire again.
	 * @return TRUE if the cooldown was expired/unset and has now been reset, FALSE otherwise.
	 */
	public static boolean tryCooldown(Player player, String key, long delay) {
		if (getCooldown(player, key) <= 0) {
			setCooldown(player, key, delay);
			return true;
		}
		return false;
	}

	/**
	 * Remove any cooldowns associated with the given player.
	 *
	 * @param player - the player we will reset.
	 */
	public static void removeCooldowns(Player player) {
		cooldowns.row(player.getName()).clear();
	}

	private static long calculateRemainder(Long expireTime) {
		return expireTime != null ? expireTime - System.currentTimeMillis() : Long.MIN_VALUE;
	}
}

