package com.example.mytestmoxyfragment.test1.test2;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.presenter.PresenterType;
import java.lang.Override;
import java.util.ArrayList;
import java.util.List;

public class Fragment1$$PresentersBinder extends PresenterBinder<Fragment1> {
	@Override
	public List<PresenterField<Fragment1>> getPresenterFields() {
		List<PresenterField<Fragment1>> presenters = new ArrayList<>(1);
		presenters.add(new mHelloWorldPresent123123erBinder());
		return presenters;
	}

	public class mHelloWorldPresent123123erBinder extends PresenterField<Fragment1> {
		public mHelloWorldPresent123123erBinder() {
			super("mHelloWorldPresent123123er", PresenterType.LOCAL, null, HolloWorldPresenter.class);
		}

		@Override
		public void bind(Fragment1 target, MvpPresenter presenter) {
			target.mHelloWorldPresent123123er = (HolloWorldPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(Fragment1 delegated) {
			return new HolloWorldPresenter();
		}
	}
}
