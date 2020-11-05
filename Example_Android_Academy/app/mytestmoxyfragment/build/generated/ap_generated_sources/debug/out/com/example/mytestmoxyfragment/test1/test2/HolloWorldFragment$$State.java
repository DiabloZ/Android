package com.example.mytestmoxyfragment.test1.test2;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import java.lang.Override;

public class HolloWorldFragment$$State extends MvpViewState<HolloWorldFragment> implements HolloWorldFragment {
	@Override
	public void showMessage(int message) {
		ShowMessageCommand showMessageCommand = new ShowMessageCommand(message);
		mViewCommands.beforeApply(showMessageCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for (HolloWorldFragment view : mViews) {
			view.showMessage(message);
		}

		mViewCommands.afterApply(showMessageCommand);
	}

	public class ShowMessageCommand extends ViewCommand<HolloWorldFragment> {
		public final int message;

		ShowMessageCommand(int message) {
			super("showMessage", AddToEndStrategy.class);

			this.message = message;
		}

		@Override
		public void apply(HolloWorldFragment mvpView) {
			mvpView.showMessage(message);
		}
	}
}
