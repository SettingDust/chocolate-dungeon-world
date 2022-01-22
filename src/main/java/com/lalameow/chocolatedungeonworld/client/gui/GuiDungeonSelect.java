package com.lalameow.chocolatedungeonworld.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

import java.io.IOException;

public class GuiDungeonSelect extends GuiScreen {

    private final GuiCreateWorld parent;
    private final String dungeonName;
    private String title;
    private GuiTextField dungeonTextField;

    public GuiDungeonSelect(GuiCreateWorld parentIn, String dungeonName) {
        this.parent = parentIn;
        this.dungeonName = dungeonName;
    }

    @Override public void initGui() {
        this.title = I18n.format("chocolateDungeonWorld.selectDungeon");
        this.dungeonTextField = new GuiTextField(0, fontRenderer, 64, 36, width - 128, 20);
        dungeonTextField.setText(dungeonName);
        this.addButton(new GuiButton(0, width / 2 - 66, 64, 64, 20, I18n.format("gui.done")));
        this.addButton(new GuiButton(1, width / 2 + 2, 64, 64, 20, I18n.format("gui.cancel")));
    }

    @Override public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, title, width / 2, 16, 0xffffff);
        dungeonTextField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        dungeonTextField.textboxKeyTyped(typedChar, keyCode);
    }

    @Override protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        dungeonTextField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                parent.chunkProviderSettingsJson = dungeonTextField.getText();
            case 1:
                this.mc.displayGuiScreen(this.parent);
        }
    }
}
