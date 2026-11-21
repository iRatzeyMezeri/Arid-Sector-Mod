package aridmod.content;

import mindustry.graphics.*;

public class AridSectorCacheLayers {
    public static CacheLayer solvent;

    public static void load(){
        CacheLayer.add(
                solvent = new CacheLayer.ShaderLayer(AridSectorShaders.solvent)
        );
    }
}
