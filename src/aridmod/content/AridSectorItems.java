package aridmod.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.content.Items;
import mindustry.type.*;

public class AridSectorItems {
    public static Item
            magnesium, itemClay, carapace;

    public static final Seq<Item> aridsectorItems = new Seq<>();

    public static void load() {
        magnesium = new Item("magnesium", Color.valueOf("32a899")) {{
            hardness = 2;
            cost = 0.5f;
        }};
        itemClay = new Item("item-clay", Color.valueOf("704036")) {{
            hardness = 1;
            cost = 0.5f;
        }};
        carapace = new Item("carapace", Color.valueOf("000000")) {{
            hardness = 2;
        }};

        aridsectorItems.addAll(
                magnesium, itemClay, carapace
        );
    }
}