# Implementation of Resource server

In this section I would like to show configuration of two main classes:
 - ResourceServerConfigurer,
 - MethodSecurityConfig. 

## Provide Spring Security configuration by ResourceServerConfigurer

In this part of implementation I add ***@EnableResourceServer*** annotation which says that componenta will be treated as a resource server. All upcomming request must be authentiacted by access token. All configuration containing 
```java
public TokenStore tokenStore()
```
```java
public DefaultTokenServices tokenServices(final TokenStore tokenStore)
```
```java
public JwtAccessTokenConverter jwtAccessTokenConverter()
```

is the same as in [OAuth2 project)[https://github.com/witosh/OAuth2/tree/master/oauth2-server].

Thanks to extending out confuiguration class by ***ResourceServerConfigurerAdapter*** we can customize configuration by overrides metod such as:
```java
public void configure(HttpSecurity http) throws Exception
```
and we allow to process method request sucha a ***POST*** and ***GET***.

## Provide Spring Security configuration by MethodSecurityConfig

For successful configured ***resource server*** we should think about access controll to the used endpoints in this service. With help comes following configuration ***resource method-level***.

In this class I add ***@EnableGlobalMethodSecurity(prePostEnabled = true)*** annotation and extend claas by ***GlobalMethodSecurityConfiguration***. What's importatnt here I setup  attribution value on ***true*** what's give abiliti to create access rule. in this case  ***prePostEnabled*** allows us to use Spring Security ***@PreAuthrize*** and ***@PostAuthorize*** annotations. In this annotation we can use Spring Expression Language to define expressio-based acces control e.g:
```java
@PreAuthorize("#oauth2.isOAuth() and #oauth2.hasScope('GITHUB') or #oauth2.hasScope('GIT')")
```
This annotation evaluate condition expression before it start execute method. In  ***@PostAuthorize*** annotation it start execute command and the evaluate expression but if condition doesn't meet condition could alter result of the method.