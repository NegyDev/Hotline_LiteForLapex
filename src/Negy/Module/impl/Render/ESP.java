package Negy.Module.impl.Render;

import Negy.Event.Event;
import Negy.Event.impl.EntityRenderEvent;
import Negy.Mappings.Minecraft;
import Negy.Module.Category;
import Negy.Module.Module;
import net.minecraft.B5;
import net.minecraft.BM;
import net.minecraft.client.Yn;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.r;
import static org.lwjgl.opengl.GL11.*;
import net.minecraft.hH;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Field;
import java.util.UUID;

public class ESP extends Module {
    public static final int NPC_UUID_VERSION = 2;
    public ESP() {
        super("ESP", 0, Category.RENDER);
        this.description = "Mapteki Oyuncuları Görmenizi Sağlar";
    }
    @Override
    public void onEvent(Event event) {
        if (event instanceof EntityRenderEvent) {
            EntityRenderEvent e = (EntityRenderEvent)event;
            for(B5 entity: Minecraft.getWorld().M) {
                if(entity == Minecraft.getPlayer())continue;
                if(entity == null)continue;
                if(entity instanceof net.minecraft.OI){
                    renderESP(entity,e.partialTicks);
                }
            }
        }
    }
    private void renderESP(B5 entity, float partialTicks) {
        if (GetUniqueID(entity).version() != NPC_UUID_VERSION) {
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);

            render2dBox((BM) entity, getRenderManager(Minecraft.getMinecraft()), partialTicks);

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
        }
    }
    public net.minecraft.client.r getRenderManager(Yn Minecraft) {
        try {
            Field field = Yn.class.getDeclaredField("h1");
            field.setAccessible(true);
            return (net.minecraft.client.r)field.get(Minecraft);
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public net.minecraft.hH getEntityBoundingBox(B5 entity) {
        try {
            Field field = B5.class.getDeclaredField("Fw");
            field.setAccessible(true);
            return (net.minecraft.hH)field.get(entity);
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public UUID GetUniqueID(B5 entity) {
        try {
            Field field = B5.class.getDeclaredField("FQ");
            field.setAccessible(true);
            return (UUID)field.get(entity);
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public double getRenderManagerField(r RenderManager,String FieldName) {
        try {
            Field field = r.class.getDeclaredField(FieldName);
            field.setAccessible(true);
            return (double)field.get(RenderManager);
        }catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    private void render2dBox(BM entity, r renderManager, float partialTicks) {
        glPushMatrix();

        hH aabb = getEntityBoundingBox(entity);
        double entityWidth = aabb.l - aabb.h;
        double entityHeight = aabb.p - aabb.s;
        double centerX = entity.x + (entity.Fy - entity.x) * partialTicks - getRenderManagerField(renderManager,"r");
        double centerY = entity.k + (entity.L - entity.k) * partialTicks - getRenderManagerField(renderManager,"B") + (entityHeight / 2);
        double centerZ = entity.F8 + (entity.F4 - entity.F8) * partialTicks - getRenderManagerField(renderManager,"g");

        glTranslated(centerX, centerY, centerZ);
        glRotatef(-renderManager.H, 0F, 1F, 0F);
        glRotatef(renderManager.x, 1F, 0F, 0F);

        float size = (float)Math.max(entityWidth, entityHeight) + 0.5F;
        float sizeFromCenter = size / 2.0F;

        GL11.glColor3f(1.0F, 0.0F, 0.0F);
        glBegin(GL_LINE_LOOP);
        glVertex2f(sizeFromCenter, sizeFromCenter);
        glVertex2f(sizeFromCenter, -sizeFromCenter);
        glVertex2f(-sizeFromCenter, -sizeFromCenter);
        glVertex2f(-sizeFromCenter, sizeFromCenter);
        glEnd();
        glPopMatrix();
    }
}
