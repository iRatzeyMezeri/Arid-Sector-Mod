package aridmod.planet;

import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.noise.*;
import mindustry.content.Blocks;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.world.Block;
import aridmod.content.AridSectorBlocks;

public class AridPlanetGenerator extends PlanetGenerator {
    public float heightScl = 1f, octaves = 8, persistence = 0.7f, heightPow = 3f, heightMult = 2.2f;

    @Override
    public float getHeight(Vec3 position){
        return Mathf.pow(noise(position), heightPow) * heightMult * Math.max(0.2f, Math.abs(position.y));
    }

    float noise(Vec3 position){
        return Simplex.noise3d(seed, octaves, persistence, 1f/heightScl, 10f + position.x, 10f + position.y, 10f + position.z);
    }

    @Override
    public void getColor(Vec3 position, Color out) {
        float height = getHeight(position);
        Block block;
        if (height < 0.01f) {
            block = Blocks.slag;
        } else if (height < 0.05f) {
            block = AridSectorBlocks.decayedSand;
        } else if (height < 0.13f) {
            block = AridSectorBlocks.clay;
        } else if (height < 0.24) {
            block = AridSectorBlocks.flint;
        } else if (height < 0.27) {
            block = AridSectorBlocks.navystone;
        } else {
            block = AridSectorBlocks.tealstone;
        }
        out.set(block.mapColor).a(1f - block.albedo);
    }
}