package it.unisa.casper.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import it.unisa.casper.gui.StatisticsTabPage;
import org.jetbrains.annotations.NotNull;

public class StatsAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {

        StatisticsTabPage config = new StatisticsTabPage();
        config.show();

    }

}