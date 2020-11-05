package com.arellomobile.mvp;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.example.mytestmoxyfragment.test1.test2.Fragment1;
import com.example.mytestmoxyfragment.test1.test2.Fragment1$$PresentersBinder;
import com.example.mytestmoxyfragment.test1.test2.HolloWorldPresenter;
import com.example.mytestmoxyfragment.test1.test2.HolloWorldPresenter$$ViewStateProvider;
import java.lang.Class;
import java.lang.Object;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MoxyReflector {
	private static Map<Class<?>, Object> sViewStateProviders;

	private static Map<Class<?>, List<Object>> sPresenterBinders;

	private static Map<Class<?>, Object> sStrategies;

	static {
		sViewStateProviders = new HashMap<>();
		sViewStateProviders.put(HolloWorldPresenter.class, new HolloWorldPresenter$$ViewStateProvider());

		sPresenterBinders = new HashMap<>();
		sPresenterBinders.put(Fragment1.class, Arrays.<Object>asList(new Fragment1$$PresentersBinder()));

		sStrategies = new HashMap<>();
		sStrategies.put(AddToEndStrategy.class, new AddToEndStrategy());
	}

	public static Object getViewState(Class<?> presenterClass) {
		ViewStateProvider viewStateProvider = (ViewStateProvider) sViewStateProviders.get(presenterClass);
		if (viewStateProvider == null) {
			return null;
		}

		return viewStateProvider.getViewState();
	}

	public static List<Object> getPresenterBinders(Class<?> delegated) {
		return sPresenterBinders.get(delegated);
	}

	public static Object getStrategy(Class<?> strategyClass) {
		return sStrategies.get(strategyClass);
	}
}
