package gay.`object`.hexxyinthealps.fabric

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import gay.`object`.hexxyinthealps.HexxyInTheAlpsClient

object FabricHexxyInTheAlpsModMenu : ModMenuApi {
    override fun getModConfigScreenFactory() = ConfigScreenFactory(HexxyInTheAlpsClient::getConfigScreen)
}
