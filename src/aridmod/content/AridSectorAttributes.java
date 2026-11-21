package aridmod.content;

import mindustry.content.Blocks;
import aridmod.content.AridSectorBlocks;
import mindustry.world.meta.*;

public class AridSectorAttributes {
    public static void load(){
        Attribute.add("clay-concentration");
    }

    public static void setAttributes(){
        AridSectorBlocks.clayWall.attributes.set(Attribute.get("clay-concentration"), 2);
        AridSectorBlocks.decayedSand.attributes.set(Attribute.get("clay-concentration"), 2);
        AridSectorBlocks.flintWall.attributes.set(Attribute.get("clay-concentration"), 1.5f);
        AridSectorBlocks.clumpSoilWall.attributes.set(Attribute.get("clay-concentration"), 1.5f);
        AridSectorBlocks.flintSilicon.attributes.set(Attribute.get("clay-concentration"), 1f);
        AridSectorBlocks.soilSilicon.attributes.set(Attribute.get("clay-concentration"), 1f);
        AridSectorBlocks.purpuriteWall.attributes.set(Attribute.get("clay-concentration"), 1f);
        AridSectorBlocks.taintedTealstoneWall.attributes.set(Attribute.get("clay-concentration"), 0.7f);
        AridSectorBlocks.tealstoneWall.attributes.set(Attribute.get("clay-concentration"), 0.3f);
        AridSectorBlocks.navystoneWall.attributes.set(Attribute.get("clay-concentration"), 0f);
    }
}