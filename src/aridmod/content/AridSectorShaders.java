package aridmod.content;

import arc.*;
import arc.graphics.*;
import arc.graphics.gl.*;
import arc.util.*;
import mindustry.graphics.*;

import static mindustry.graphics.Shaders.*;
import static mindustry.Vars.*;

public class AridSectorShaders {

    public static SurfaceShader solvent;

    public static void load(){
        solvent = new SurfaceShader("solvent");
    }
}