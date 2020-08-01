/*
 *
 *  *
 *  *  *
 *  *  *  * MegaEconomy-Expansion - An expansion for the MegaEconomy plugin.
 *  *  *  * Copyright (C) 2018 Max Berkelmans AKA LemmoTresto
 *  *  *  *
 *  *  *  * This program is free software: you can redistribute it and/or modify
 *  *  *  * it under the terms of the GNU General Public License as published by
 *  *  *  * the Free Software Foundation, either version 3 of the License, or
 *  *  *  * (at your option) any later version.
 *  *  *  *
 *  *  *  * This program is distributed in the hope that it will be useful,
 *  *  *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *  *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  *  *  * GNU General Public License for more details.
 *  *  *  *
 *  *  *  * You should have received a copy of the GNU General Public License
 *  *  *  * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *  *  *
 *  *
 *
 */

package me.max.megaeconomyexpansion;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.max.megaeconomy.MegaEconomy;
import me.max.megaeconomy.api.Api;
import me.max.megaeconomy.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

public class MegaEconomyExpansion extends PlaceholderExpansion {


    Api api = MegaEconomy.getApi();

    /**
     * If MegaEconomy is null or disable return false else return true.
     * @return boolean if megaeconomy is enabled/not null.
     */
    @Override
    public boolean canRegister() {
        if (api == null) return false;
        Plugin pl = Bukkit.getPluginManager().getPlugin(getRequiredPlugin());
        if (pl == null || !pl.isEnabled()) return false;
        return true;
    }

    /**
     * @return LemmoTresto which is the name of the author.
     */
    @Override
    public String getAuthor() {
        return "LemmoTresto";
    }

    /**
     * @return String identifier
     */
    @Override
    public String getIdentifier() {
        return "megaeconomy";
    }

    /**
     * @return MegaEconomy
     */
    @Override
    public String getRequiredPlugin() {
        return "MegaEconomy";
    }

    /**
     * @return version of our expansion
     */
    @Override
    public String getVersion() {
        return "1.0.0";
    }

    /**
     * This method gets called when we need to parse a placeholder.
     * @return String outcome of the placeholder.
     */
    @Override
    public String onRequest(OfflinePlayer p, String identifier) {

        if (api == null) return null;

        if (identifier.split("_")[0].equalsIgnoreCase("balance")){
            Economy economy = api.getEconomy(identifier.split("_")[1]);
            if (economy == null) return null;
            return api.getBalanceOfPlayer(p, economy).toString();
        }


        return null;
    }
}
