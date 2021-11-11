import { render, screen } from '@testing-library/react';
import App from './App';

describe('Test main page rendering', () => {
  const OLD_ENV = process.env;

  beforeEach(() => {
    jest.resetModules()
    process.env = { ...OLD_ENV };
  });

  afterAll(() => {
    process.env = OLD_ENV;
  });

  test('renders learn react link', () => {
    window = {}
    window['_env_'] = {}
    window._env_['API_URL'] = 'http://local.local'
    const result = render(<App />);
    const examList = result.container.querySelector('.examList');
    expect(examList).toBeInTheDocument();
  });
});