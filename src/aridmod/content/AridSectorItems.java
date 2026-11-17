package aridmod.content;

import arc.graphics.*;
import arc.struct.*;
import aridmod.ASPal;
import mindustry.content.Items;
import mindustry.type.*;

public class AridSectorItems {
    public static Item
            aluminium, itemClay, carapace;

    public static final Seq<Item> aridsectorItems = new Seq<>();

    public static void load() {
        aluminium = new Item("magnesium", ASPal.electricLM) {{
            hardness = 2;
            cost = 0.5f;
        }};
        itemClay = new Item("item-clay", ASPal.earthlyLM) {{
            hardness = 1;
            cost = 0.5f;
        }};
        carapace = new Item("carapace", ASPal.biomaterialLM) {{
            hardness = 2;
        }};

        aridsectorItems.addAll(
                aluminium, itemClay, carapace
        );
    }
}