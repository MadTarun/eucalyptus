package com.eucalyptus.webui.client;

import com.eucalyptus.webui.client.place.ErrorSinkPlace;
import com.eucalyptus.webui.client.place.StartPlace;
import com.eucalyptus.webui.client.service.EucalyptusService;
import com.eucalyptus.webui.client.service.EucalyptusServiceAsync;
import com.eucalyptus.webui.client.session.LocalSession;
import com.eucalyptus.webui.client.session.LocalSessionImpl;
import com.eucalyptus.webui.client.session.SessionData;
import com.eucalyptus.webui.client.view.AccountView;
import com.eucalyptus.webui.client.view.AccountViewImpl;
import com.eucalyptus.webui.client.view.DetailView;
import com.eucalyptus.webui.client.view.DetailViewImpl;
import com.eucalyptus.webui.client.view.ErrorSinkView;
import com.eucalyptus.webui.client.view.ErrorSinkViewImpl;
import com.eucalyptus.webui.client.view.LoadingAnimationView;
import com.eucalyptus.webui.client.view.LoadingAnimationViewImpl;
import com.eucalyptus.webui.client.view.LoadingProgressView;
import com.eucalyptus.webui.client.view.LoadingProgressViewImpl;
import com.eucalyptus.webui.client.view.LoginView;
import com.eucalyptus.webui.client.view.LoginViewImpl;
import com.eucalyptus.webui.client.view.ConfigView;
import com.eucalyptus.webui.client.view.ConfigViewImpl;
import com.eucalyptus.webui.client.view.ReportView;
import com.eucalyptus.webui.client.view.ReportViewImpl;
import com.eucalyptus.webui.client.view.ShellView;
import com.eucalyptus.webui.client.view.ShellViewImpl;
import com.eucalyptus.webui.client.view.StartView;
import com.eucalyptus.webui.client.view.StartViewImpl;
import com.eucalyptus.webui.client.view.VmTypeView;
import com.eucalyptus.webui.client.view.VmTypeViewImpl;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.ResettableEventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;

public class ClientFactoryImpl implements ClientFactory {
  
  private static final Place DEFAULT_PLACE = new StartPlace( );
  private static final Place ERROR_PLACE = new ErrorSinkPlace( );
  
	private EventBus mainEventBus = new ResettableEventBus( new SimpleEventBus( ) );
	private PlaceController mainPlaceController = new PlaceController( mainEventBus );
  private ActivityManager mainActivityManager;
  private PlaceHistoryHandler mainPlaceHistoryHandler;
  private Historian mainHistorian = new PlaceHistoryHandler.DefaultHistorian( );
	
	private EventBus lifecycleEventBus = new SimpleEventBus( );
	private PlaceController lifecyclePlaceController = new PlaceController( lifecycleEventBus );
	
	private LocalSession localSession = new LocalSessionImpl( );
	private SessionData sessionData = new SessionData( );
	
	private EucalyptusServiceAsync backendService = GWT.create( EucalyptusService.class );

	private LoginView loginView;
	private LoadingProgressView loadingProgressView;
	private ShellView shellView;
	private StartView startView;
	private ConfigView configView;
	private LoadingAnimationView loadingAnimationView;
	private ErrorSinkView errorSinkView;
	private AccountView accountView;
	private VmTypeView vmTypeView;
	private ReportView reportView;

  @Override
  public LocalSession getLocalSession( ) {
    return localSession;
  }

  @Override
  public EucalyptusServiceAsync getBackendService( ) {
    return backendService;
  }

  @Override
  public EventBus getMainEventBus( ) {
    return mainEventBus;
  }

  @Override
  public PlaceController getMainPlaceController( ) {
    return mainPlaceController;
  }

  @Override
  public EventBus getLifecycleEventBus( ) {
    return lifecycleEventBus;
  }

  @Override
  public PlaceController getLifecyclePlaceController( ) {
    return lifecyclePlaceController;
  }
  
  @Override
  public LoginView getLoginView( ) {
    if ( loginView == null ) {
      loginView = new LoginViewImpl( );
    }
    return loginView;
  }
  
  @Override
  public ShellView getShellView( ) {
    if ( shellView == null ) {
      shellView = new ShellViewImpl( );
    }
    return shellView;
  }

  @Override
  public StartView getStartView( ) {
    if ( startView == null ) {
      startView = new StartViewImpl( );
    }
    return startView;
  }
  
  @Override
  public LoadingProgressView getLoadingProgressView( ) {
    if ( loadingProgressView == null ) {
      loadingProgressView = new LoadingProgressViewImpl( );
    }
    return loadingProgressView;
  }

  @Override
  public ConfigView getConfigView( ) {
    if ( configView == null ) {
      configView = new ConfigViewImpl( );
    }
    return configView;
  }  
  
  @Override
  public LoadingAnimationView getLoadingAnimationView( ) {
    if ( loadingAnimationView == null ) {
      loadingAnimationView = new LoadingAnimationViewImpl( );
    }
    return loadingAnimationView;
  }

  @Override
  public ErrorSinkView getErrorSinkView( ) {
    if ( errorSinkView == null ) {
      errorSinkView = new ErrorSinkViewImpl( );
    }
    return errorSinkView;
  }

  @Override
  public AccountView getAccountView( ) {
    if ( accountView == null ) {
      accountView = new AccountViewImpl( );
    }
    return accountView;
  }
  
  @Override
  public VmTypeView getVmTypeView( ) {
    if ( vmTypeView == null ) {
      vmTypeView = new VmTypeViewImpl( );
    }
    return vmTypeView;
  }
  
  @Override
  public ReportView getReportView( ) {
    if ( reportView == null ) {
      reportView = new ReportViewImpl( );
    }
    return reportView;
  }
  
  @Override
  public ActivityManager getMainActivityManager( ) {
    if ( mainActivityManager == null ) {
      ActivityMapper activityMapper = new MainActivityMapper( this );
      mainActivityManager = new ActivityManager( activityMapper, mainEventBus );      
    }
    return mainActivityManager;
  }

  @Override
  public PlaceHistoryHandler getMainPlaceHistoryHandler( ) {
    if ( mainPlaceHistoryHandler == null ) {
      MainPlaceHistoryMapper historyMapper= GWT.create( MainPlaceHistoryMapper.class );
      mainPlaceHistoryHandler = new ExPlaceHistoryHandler( historyMapper );    
      ( ( ExPlaceHistoryHandler ) mainPlaceHistoryHandler).register( mainPlaceController, mainEventBus, DEFAULT_PLACE, ERROR_PLACE );      
    }
    return mainPlaceHistoryHandler;
  }

  @Override
  public Historian getMainHistorian( ) {
    return mainHistorian;
  }

  @Override
  public Place getDefaultPlace( ) {
    return DEFAULT_PLACE;
  }
  
  @Override
  public Place getErrorPlace( ) {
    return ERROR_PLACE;
  }

  @Override
  public SessionData getSessionData( ) {
    return sessionData;
  }
  
}